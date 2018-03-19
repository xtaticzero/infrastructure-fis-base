/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 *
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.common.correo.services.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import mx.gob.sat.siat.base.service.BaseSupportServices;
import mx.gob.sat.siat.common.correo.exception.MailException;
import mx.gob.sat.siat.common.correo.model.MailMessage;
import mx.gob.sat.siat.common.correo.model.MailMessageAttachment;
import mx.gob.sat.siat.common.correo.services.MailServices;


public class MailServicesJndiSunImpl extends BaseSupportServices implements MailServices, ApplicationContextAware {

    
    private transient JavaMailSenderImpl sender;
    private static final long serialVersionUID = 5848841835487882428L;
    private static final String MAIL_FROM = "mail.from";
	private static final String MAIL_PERSONAL_FROM = "mail.personal_from";
	private static final String MAIL_SMTP_HOST = "mail.smtp.host";
	private static final String MAIL_SMTP_PORT = "mail.smtp.port";
	private static final String MAIL_SMTP_USER = "mail.smtp.user";
	private static final String MAIL_SMTP_PASSWORD = "mail.smtp.password";
    
    /**
     * Variable de contexto
     */
    private transient ApplicationContext applicationContext;

    protected MailServicesJndiSunImpl() {
        super();
    }

   

    @Override
    public void sendHTMLMessage(final MailMessage mailMessage) throws MailException {
        if (null != mailMessage) {
            MimeMessage message = sender.createMimeMessage();
            preparedSender(sender);
            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                String[] tos = new String[mailMessage.getTo().size()];

                helper.setTo(mailMessage.getTo().toArray(tos));
                helper.setText(mailMessage.getBody(), true);
                
				String fromSession = sender.getSession().getProperty(MAIL_FROM);
				String fromPersonalSession = sender.getSession().getProperty(MAIL_PERSONAL_FROM);

                if (null == fromPersonalSession) {
                	fromPersonalSession = fromSession;
                }

				helper.setFrom(fromSession, fromPersonalSession);

                if (null != mailMessage.getCc() && !mailMessage.getCc().isEmpty()) {
                    String[] ccs = new String[mailMessage.getCc().size()];

                    helper.setCc(mailMessage.getCc().toArray(ccs));
                }

                if (null != mailMessage.getBcc() && !mailMessage.getBcc().isEmpty()) {
                    String[] bccs = new String[mailMessage.getCc().size()];

                    helper.setBcc(mailMessage.getBcc().toArray(bccs));
                }

                helper.setSubject(mailMessage.getSubject());
                if (null != mailMessage.getImagenesInlIne()) {
                    for(Entry<String, String> elemento : mailMessage.getImagenesInlIne().entrySet()){
                        FileSystemResource res = new FileSystemResource(elemento.getValue());
                        helper.addInline(elemento.getKey(), res);
                    }
                }
                
                Collection<MailMessageAttachment> attachments = mailMessage.getAttachments();	            

	            if (attachments != null && !attachments.isEmpty()) {
	                logger.debug("attachments - size: [{}]", attachments.size());
	                DataSource dataSource = null;
	                for (MailMessageAttachment attachment : attachments) {
	                    dataSource = new ByteArrayDataSource(attachment.getContent(), attachment.getContentType());	                    
	                    helper.addAttachment(attachment.getName(), dataSource);
	                }
	            }
                
                logger.debug("Cuerpo del correo enviado: ");
                logger.debug(mailMessage.getBody());
                sender.send(message);

            }
            catch (MessagingException e) {
                logger.error("", e);
                throw new MailException(e.getMessage(), e);
            }
            catch (UnsupportedEncodingException e) {
                logger.error("", e);
                throw new MailException(e.getMessage(), e);
            }

        }
    }

    private void preparedSender(JavaMailSenderImpl sender) {
        sender.setHost(sender.getSession().getProperty(MAIL_SMTP_HOST));
        sender.setPort(Integer.parseInt(sender.getSession().getProperty(MAIL_SMTP_PORT)));
        sender.setUsername(sender.getSession().getProperty(MAIL_SMTP_USER));
        sender.setPassword(sender.getSession().getProperty(MAIL_SMTP_PASSWORD));
    }

    @Override
    public void sendTextMessage(final MailMessage mailMessage) throws MailException {
        if (null != mailMessage) {
            try {
                Multipart multipart = new MimeMultipart();

                final MimeBodyPart messageBodyPart = new MimeBodyPart();

                messageBodyPart.setContent(mailMessage.getBody(), "text/plain");

                multipart.addBodyPart(messageBodyPart);

                multipart = prepareAttachments(multipart, mailMessage);

                final Message message = prepareMessage(mailMessage);

                message.setContent(multipart);

                Transport.send(message);
            }
            catch (MessagingException me) {
                throw new MailException(me.getMessage(), me);
            }
            catch (IOException ioe) {
                throw new MailException(ioe.getMessage(), ioe);
            }
        }
    }
    
    @Override
    public void getMessagesFromInbox() {
    	// TODO Auto-generated method stub
    	
    }

    private MimeMessage prepareMessage(final MailMessage mailMessage) throws MailException {
        logger.debug("mail message: [{}]", mailMessage);

        final MimeMessage message = sender.createMimeMessage();
        try {
            if (null == mailMessage.getPersonalFrom()) {
                message.setFrom(buildInternetAddress(sender.getSession().getProperty(MAIL_FROM)));
            }
            else {
                message.setFrom(buildInternetAddress(sender.getSession().getProperty(MAIL_FROM), mailMessage.getPersonalFrom()));
            }

            message.setRecipients(Message.RecipientType.TO, buildAddresses(mailMessage.getTo()));

            if (null != mailMessage.getCc() && !mailMessage.getCc().isEmpty()) {
                message.setRecipients(Message.RecipientType.CC, buildAddresses(mailMessage.getCc()));
            }

            if (null != mailMessage.getBcc() && !mailMessage.getBcc().isEmpty()) {
                message.setRecipients(Message.RecipientType.BCC, buildAddresses(mailMessage.getBcc()));
            }

            message.setSubject(mailMessage.getSubject(), "iso-8859-1");
        }
        catch (AddressException ae) {
            throw new MailException("Invalid e-mail address.", ae);
        }
        catch (MessagingException me) {
            throw new MailException(me);
        }

        return message;
    }

    private InternetAddress[] buildAddresses(final Collection<String> addresses) throws MailException {
        logger.debug("addresses: [{}]", addresses);

        if (null != addresses && !addresses.isEmpty()) {
            final Collection<InternetAddress> mailAddresses = new ArrayList<InternetAddress>();
            InternetAddress mailAddress = null;

            for (final String address : addresses) {
                logger.debug("address: [{}]", address);

                if (null != address && !address.trim().isEmpty()) {
                    mailAddress = buildInternetAddress(address);

                    mailAddresses.add(mailAddress);
                }
            }

            logger.debug("mail addresses: [{}]", mailAddresses);

            return mailAddresses.toArray(new InternetAddress[0]);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    private InternetAddress buildInternetAddress(final String eMailAddress) throws MailException {
        InternetAddress internetAddress = null;

        if (null != eMailAddress && !eMailAddress.trim().isEmpty()
                && EmailValidator.getInstance().isValid(eMailAddress)) {
            try {
                internetAddress = new InternetAddress(eMailAddress);
            }
            catch (AddressException ae) {
                throw new MailException(eMailAddress, ae);
            }
        }
        else {
            throw new MailException(eMailAddress);
        }

        return internetAddress;
    }

    private InternetAddress buildInternetAddress(final String eMailAddress, final String personalAddress)
            throws MailException {
        InternetAddress internetAddress = buildInternetAddress(eMailAddress);

        try {
            internetAddress.setPersonal(personalAddress);
        }
        catch (UnsupportedEncodingException uee) {
            throw new MailException(uee.getMessage(), uee);
        }

        return internetAddress;
    }

    private Multipart prepareAttachments(final Multipart multipart, final MailMessage mailMessage) throws IOException,
            MessagingException {
        MimeBodyPart messageBodyPart = null;

        Collection<String> attachmentPaths = mailMessage.getAttachmentPaths();

        logger.debug("attachment paths: [{}]", attachmentPaths);
        logger.debug("attachment paths - size: [{}]", attachmentPaths.size());

        if (!attachmentPaths.isEmpty()) {
            for (String path : attachmentPaths) {
                messageBodyPart = new MimeBodyPart();

                messageBodyPart.attachFile(path);

                multipart.addBodyPart(messageBodyPart);
            }
        }

        Collection<MailMessageAttachment> attachments = mailMessage.getAttachments();

        logger.debug("attachments: [{}]", attachments);

        if (attachments != null && !attachments.isEmpty()) {
            logger.debug("attachments - size: [{}]", attachments.size());

            DataSource dataSource;

            for (MailMessageAttachment attachment : attachments) {
                logger.debug("attachment: [{}]", attachment);

                dataSource = new ByteArrayDataSource(attachment.getContent(), attachment.getContentType());

                messageBodyPart = new MimeBodyPart();

                messageBodyPart.setDataHandler(new DataHandler(dataSource));
                messageBodyPart.setFileName(attachment.getName());

                multipart.addBodyPart(messageBodyPart);
            }
        }

        return multipart;
    }

   
    /**
     * Metodo para la aplicacion del contexto
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
	
	public JavaMailSenderImpl getSender() {
		return sender;
	}

	public void setSender(JavaMailSenderImpl sender) {
		this.sender = sender;
	}

    public final ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}

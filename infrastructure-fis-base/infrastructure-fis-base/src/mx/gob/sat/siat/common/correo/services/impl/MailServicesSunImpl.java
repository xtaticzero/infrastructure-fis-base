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
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
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

public class MailServicesSunImpl extends BaseSupportServices implements MailServices, ApplicationContextAware {

    /**
     *
     */
    private static final long serialVersionUID = 5848841835487882428L;
    @Value("${mail_services.debug}")
    private String debug;
    @Value("${mail_services.charSet}")
    private String charSet;
    @Value("${mail_services.smtp_useSSL}")
    private String smtpUseSSL;
    @Value("${mail_services.smtp_host}")
    private String smtpHost;
    @Value("${mail_services.smtp_port}")
    private String smtpPort;
    @Value("${mail_services.smtp_username}")
    private String smtpUsername;
    @Value("${mail_services.smtp_password}")
    private String smtpPassword;
    @Value("${mail_services.smtp_connectionTimeout}")
    private String smtpConnectionTimeout;
    @Value("${mail_services.smtp_timeout}")
    private String smtpTimeout;
    @Value("${mail_services.smtp_quitWait}")
    private String smtpQuitWait;

    protected MailServicesSunImpl() {
        super();
    }

    public void setDebug(final String debug) {
        this.debug = debug;
    }

    public void setCharSet(final String charSet) {
        this.charSet = charSet;
    }

    public void setSmtpUseSSL(final String smtpUseSSL) {
        this.smtpUseSSL = smtpUseSSL;
    }

    public void setSmtpHost(final String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public void setSmtpPort(final String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public void setSmtpUsername(final String smtpUsername) {
        this.smtpUsername = smtpUsername;
    }

    public void setSmtpPassword(final String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }

    public void setSmtpConnectionTimeout(final String smtpConnectionTimeout) {
        this.smtpConnectionTimeout = smtpConnectionTimeout;
    }

    public void setSmtpTimeout(final String smtpTimeout) {
        this.smtpTimeout = smtpTimeout;
    }

    public void setSmtpQuitWait(final String smtpQuitWait) {
        this.smtpQuitWait = smtpQuitWait;
    }

    @Override
    public void sendHTMLMessage(final MailMessage mailMessage) throws MailException {

        logger.debug("user {}", smtpUsername);
        if (null != mailMessage) {
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            prepareJavaMailSender(sender);
            MimeMessage message = sender.createMimeMessage();
            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                String[] tos = new String[mailMessage.getTo().size()];

                helper.setTo(mailMessage.getTo().toArray(tos));
                helper.setText(mailMessage.getBody(), true);

                if (null == mailMessage.getPersonalFrom()) {
                    helper.setFrom(mailMessage.getFrom());
                } else {
                    helper.setFrom(mailMessage.getFrom(), mailMessage.getPersonalFrom());
                }

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
                    for (Entry<String, String> elemento : mailMessage.getImagenesInlIne().entrySet()) {
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

                sender.send(message);

            } catch (MessagingException e) {
                logger.error("", e);
                throw new MailException(e.getMessage(), e);
            } catch (UnsupportedEncodingException e) {
                logger.error("", e);
                throw new MailException(e.getMessage(), e);
            }

        }
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
            } catch (MessagingException me) {
                throw new MailException(me.getMessage(), me);
            } catch (IOException ioe) {
                throw new MailException(ioe.getMessage(), ioe);
            }
        }
    }

    @Override
    public void getMessagesFromInbox() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        prepareJavaMailSender(sender);
        Session session = sender.getSession();

        try {
            Store store = session.getStore();

            Folder folder = store.getFolder("TAXI");

            folder.open(Folder.READ_WRITE);

            Message[] messages = folder.getMessages();

            for (Message message : messages) {
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                try {
                    System.out.println("Text: " + message.getContent().toString());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void prepareJavaMailSender(JavaMailSenderImpl sender) {
        final Properties props = new Properties();

        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.ssl.enable", "true".equals(smtpUseSSL) ? "true" : "false");
        props.put("mail.smtp.starttls.enable", "true".equals(smtpUseSSL) ? "true" : "false");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true".equals(debug) ? "true" : "false");
        props.put("mail.smtp.connectiontimeout", smtpConnectionTimeout);
        props.put("mail.smtp.timeout", smtpTimeout);
        props.put("mail.smtp.quitwait", "true".equals(smtpQuitWait) ? "true" : "false");

        sender.setJavaMailProperties(props);

        Session session = Session.getInstance(props, new SMTPAuthenticator(smtpUsername, smtpPassword));

        sender.setSession(session);
    }

    private MimeMessage prepareMessage(final MailMessage mailMessage) throws MailException {
        logger.debug("mail message: [{}]", mailMessage);

        final Properties props = new Properties();

        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.ssl.enable", "true".equals(smtpUseSSL) ? "true" : "false");
        props.put("mail.smtp.starttls.enable", "true".equals(smtpUseSSL) ? "true" : "false");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", String.valueOf(smtpPort));
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true".equals(debug) ? "true" : "false");
        props.put("mail.smtp.connectiontimeout", String.valueOf(smtpConnectionTimeout));
        props.put("mail.smtp.timeout", String.valueOf(smtpTimeout));
        props.put("mail.smtp.quitwait", "true".equals(smtpQuitWait) ? "true" : "false");

        Session session = Session.getInstance(props, new SMTPAuthenticator(smtpUsername, smtpPassword));

        logger.debug("user {}", smtpUsername);
        logger.debug("pwd {}", smtpPassword);
        logger.debug("properties: [{}]", session.getProperties());
        logger.debug("character set: [{}]", charSet);

        final MimeMessage message = new MimeMessage(session);

        try {
            if (null == mailMessage.getPersonalFrom()) {
                message.setFrom(buildInternetAddress(mailMessage.getFrom()));
            } else {
                message.setFrom(buildInternetAddress(mailMessage.getFrom(), mailMessage.getPersonalFrom()));
            }

            message.setRecipients(Message.RecipientType.TO, buildAddresses(mailMessage.getTo()));

            if (null != mailMessage.getCc() && !mailMessage.getCc().isEmpty()) {
                message.setRecipients(Message.RecipientType.CC, buildAddresses(mailMessage.getCc()));
            }

            if (null != mailMessage.getBcc() && !mailMessage.getBcc().isEmpty()) {
                message.setRecipients(Message.RecipientType.BCC, buildAddresses(mailMessage.getBcc()));
            }

            message.setSubject(mailMessage.getSubject(), charSet);
        } catch (AddressException ae) {
            throw new MailException("Invalid e-mail address.", ae);
        } catch (MessagingException me) {
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
        } else {
            throw new IllegalArgumentException();
        }
    }

    private InternetAddress buildInternetAddress(final String eMailAddress) throws MailException {
        InternetAddress internetAddress = null;

        if (null != eMailAddress && !eMailAddress.trim().isEmpty()
                && EmailValidator.getInstance().isValid(eMailAddress)) {
            try {
                internetAddress = new InternetAddress(eMailAddress);
            } catch (AddressException ae) {
                throw new MailException(eMailAddress, ae);
            }
        } else {
            throw new MailException(eMailAddress);
        }

        return internetAddress;
    }

    private InternetAddress buildInternetAddress(final String eMailAddress, final String personalAddress)
            throws MailException {
        InternetAddress internetAddress = buildInternetAddress(eMailAddress);

        try {
            internetAddress.setPersonal(personalAddress);
        } catch (UnsupportedEncodingException uee) {
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

        if (attachments != null && !attachments.isEmpty()) {
            logger.debug("attachments - size: [{}]", attachments.size());

            DataSource dataSource;

            for (MailMessageAttachment attachment : attachments) {

                dataSource = new ByteArrayDataSource(attachment.getContent(), attachment.getContentType());

                messageBodyPart = new MimeBodyPart();

                messageBodyPart.setDataHandler(new DataHandler(dataSource));
                messageBodyPart.setFileName(attachment.getName());

                multipart.addBodyPart(messageBodyPart);
            }
        }

        return multipart;
    }

    private static class SMTPAuthenticator extends Authenticator {

        private final String userName;
        private final String password;

        public SMTPAuthenticator(final String userName, final String password) {
            super();

            this.userName = userName;
            this.password = password;
        }

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(userName, password);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // TODO Auto-generated method stub
    }
}

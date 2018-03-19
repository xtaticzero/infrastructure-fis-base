package mx.gob.sat.siat.base.excepcion;

public class FileSystemException extends BusinessException {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private static final String CATEGORY = "file";

	public FileSystemException(final String situation) {
		super(CATEGORY, situation);
	}

	public FileSystemException(final String situation, final Throwable cause) {
		super(CATEGORY, situation, cause);
	}

	public FileSystemException(final String situation, final Object... args) {
		super(CATEGORY, situation, args);
	}

	public FileSystemException(final String situation, final Throwable cause,
			final Object... args) {
		super(CATEGORY, situation, cause, args);
	}
}

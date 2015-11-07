package com.meshyog.controllerservlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.security.AccessController;
import java.util.Formatter;
import java.util.Locale;
import java.util.Objects;

import sun.security.action.GetPropertyAction;

public class MeshyogPrintWritter  extends PrintWriter{
	

	protected Writer out;
	private final boolean autoFlush;
	private boolean trouble;
	private Formatter formatter;
	private PrintStream psOut;
	private final String lineSeparator;

	private static Charset toCharset(String paramString)
			throws UnsupportedEncodingException {
		Objects.requireNonNull(paramString, "charsetName");
		try {
			return Charset.forName(paramString);
		} catch (UnsupportedCharsetException localUnsupportedCharsetException) {
			throw new UnsupportedEncodingException(paramString);
		}
	}

	public MeshyogPrintWritter(Writer paramWriter) {
		this(paramWriter, false);
	}

	public MeshyogPrintWritter(Writer paramWriter, boolean paramBoolean) {
		super(paramWriter);
		this.trouble = false;
		this.psOut = null;
		this.out = paramWriter;
		this.autoFlush = paramBoolean;
		this.lineSeparator = ((String) AccessController
				.doPrivileged(new GetPropertyAction("line.separator")));
	}

	public MeshyogPrintWritter(OutputStream paramOutputStream) {
		this(paramOutputStream, false);
	}

	public MeshyogPrintWritter(OutputStream paramOutputStream, boolean paramBoolean) {
		this(new BufferedWriter(new OutputStreamWriter(paramOutputStream)),
				paramBoolean);
		if (!(paramOutputStream instanceof PrintStream))
			return;
		this.psOut = ((PrintStream) paramOutputStream);
	}

	public MeshyogPrintWritter(String paramString) throws FileNotFoundException {
		this(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
				paramString))), false);
	}

	private MeshyogPrintWritter(Charset paramCharset, File paramFile)
			throws FileNotFoundException {
		this(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
				paramFile), paramCharset)), false);
	}

	public MeshyogPrintWritter(String paramString1, String paramString2)
			throws FileNotFoundException, UnsupportedEncodingException {
		this(toCharset(paramString2), new File(paramString1));
	}

	public MeshyogPrintWritter(File paramFile) throws FileNotFoundException {
		this(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
				paramFile))), false);
	}

	public MeshyogPrintWritter(File paramFile, String paramString)
			throws FileNotFoundException, UnsupportedEncodingException {
		this(toCharset(paramString), paramFile);
	}

	private void ensureOpen() throws IOException {
		if (this.out != null)
			return;
		throw new IOException("Stream closed");
	}

	public void flush() {
		try {
			synchronized (this.lock) {
				ensureOpen();
				this.out.flush();
			}
		} catch (IOException localIOException) {
			this.trouble = true;
		}
	}

	public void close() {
		try {
			synchronized (this.lock) {
				if (this.out == null)
					return;
				this.out.close();
				this.out = null;
			}
		} catch (IOException localIOException) {
			this.trouble = true;
		}
	}

	public boolean checkError() {
		if (this.out != null)
			flush();
		if (this.out instanceof PrintWriter) {
			PrintWriter localPrintWriter = (PrintWriter) this.out;
			return localPrintWriter.checkError();
		}
		if (this.psOut != null)
			return this.psOut.checkError();
		return this.trouble;
	}

	protected void setError() {
		this.trouble = true;
	}

	protected void clearError() {
		this.trouble = false;
	}

	public void write(int paramInt) {
		try {
			synchronized (this.lock) {
				ensureOpen();
				this.out.write(paramInt);
			}
		} catch (InterruptedIOException localInterruptedIOException) {
			Thread.currentThread().interrupt();
		} catch (IOException localIOException) {
			this.trouble = true;
		}
	}

	public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2) {
		try {
			synchronized (this.lock) {
				ensureOpen();
				this.out.write(paramArrayOfChar, paramInt1, paramInt2);
			}
		} catch (InterruptedIOException localInterruptedIOException) {
			Thread.currentThread().interrupt();
		} catch (IOException localIOException) {
			this.trouble = true;
		}
	}

	public void write(char[] paramArrayOfChar) {
		write(paramArrayOfChar, 0, paramArrayOfChar.length);
	}

	public void write(String paramString, int paramInt1, int paramInt2) {
		try {
			synchronized (this.lock) {
				ensureOpen();
				this.out.write(paramString, paramInt1, paramInt2);
			}
		} catch (InterruptedIOException localInterruptedIOException) {
			Thread.currentThread().interrupt();
		} catch (IOException localIOException) {
			this.trouble = true;
		}
	}

	public void write(String paramString) {
		write(paramString, 0, paramString.length());
	}

	private void newLine() {
		try {
			synchronized (this.lock) {
				ensureOpen();
				this.out.write(this.lineSeparator);
				if (this.autoFlush)
					this.out.flush();
			}
		} catch (InterruptedIOException localInterruptedIOException) {
			Thread.currentThread().interrupt();
		} catch (IOException localIOException) {
			this.trouble = true;
		}
	}

	public void print(boolean paramBoolean) {
		write((paramBoolean) ? "true" : "false");
	}

	public void print(char paramChar) {
		write(paramChar);
	}

	public void print(int paramInt) {
		write(String.valueOf(paramInt));
	}

	public void print(long paramLong) {
		write(String.valueOf(paramLong));
	}

	public void print(float paramFloat) {
		write(String.valueOf(paramFloat));
	}

	public void print(double paramDouble) {
		write(String.valueOf(paramDouble));
	}

	public void print(char[] paramArrayOfChar) {
		write(paramArrayOfChar);
	}

	public void print(String paramString) {
		if (paramString == null)
			paramString = "null";
		write(paramString);
	}

	public void print(Object paramObject) {
		write(String.valueOf(paramObject));
	}

	public void println() {
		newLine();
	}

	public void println(boolean paramBoolean) {
		synchronized (this.lock) {
			print(paramBoolean);
			println();
		}
	}

	public void println(char paramChar) {
		synchronized (this.lock) {
			print(paramChar);
			println();
		}
	}

	public void println(int paramInt) {
		synchronized (this.lock) {
			print(paramInt);
			println();
		}
	}

	public void println(long paramLong) {
		synchronized (this.lock) {
			print(paramLong);
			println();
		}
	}

	public void println(float paramFloat) {
		synchronized (this.lock) {
			print(paramFloat);
			println();
		}
	}

	public void println(double paramDouble) {
		synchronized (this.lock) {
			print(paramDouble);
			println();
		}
	}

	public void println(char[] paramArrayOfChar) {
		synchronized (this.lock) {
			print(paramArrayOfChar);
			println();
		}
	}

	public void println(String paramString) {
		synchronized (this.lock) {
			print(paramString);
			println();
		}
	}

	public void println(Object paramObject) {
		String str = String.valueOf(paramObject);
		synchronized (this.lock) {
			print(str);
			println();
		}
	}

	public MeshyogPrintWritter printf(String paramString, Object[] paramArrayOfObject) {
		return format(paramString, paramArrayOfObject);
	}

	public MeshyogPrintWritter printf(Locale paramLocale, String paramString,
			Object[] paramArrayOfObject) {
		return format(paramLocale, paramString, paramArrayOfObject);
	}

	public MeshyogPrintWritter format(String paramString, Object[] paramArrayOfObject) {
		try {
			synchronized (this.lock) {
				ensureOpen();
				if ((this.formatter == null)
						|| (this.formatter.locale() != Locale.getDefault()))
					this.formatter = new Formatter(this);
				this.formatter.format(Locale.getDefault(), paramString,
						paramArrayOfObject);
				if (this.autoFlush)
					this.out.flush();
			}
		} catch (InterruptedIOException localInterruptedIOException) {
			Thread.currentThread().interrupt();
		} catch (IOException localIOException) {
			this.trouble = true;
		}
		return this;
	}

	public MeshyogPrintWritter format(Locale paramLocale, String paramString,
			Object[] paramArrayOfObject) {
		try {
			synchronized (this.lock) {
				ensureOpen();
				if ((this.formatter == null)
						|| (this.formatter.locale() != paramLocale))
					this.formatter = new Formatter(this, paramLocale);
				this.formatter.format(paramLocale, paramString,
						paramArrayOfObject);
				if (this.autoFlush)
					this.out.flush();
			}
		} catch (InterruptedIOException localInterruptedIOException) {
			Thread.currentThread().interrupt();
		} catch (IOException localIOException) {
			this.trouble = true;
		}
		return this;
	}

	public MeshyogPrintWritter append(CharSequence paramCharSequence) {
		if (paramCharSequence == null)
			write("null");
		else
			write(paramCharSequence.toString());
		return this;
	}

	public MeshyogPrintWritter append(CharSequence paramCharSequence, int paramInt1,
			int paramInt2) {
		CharSequence localCharSequence = (paramCharSequence == null) ? "null"
				: paramCharSequence;
		write(localCharSequence.subSequence(paramInt1, paramInt2).toString());
		return this;
	}

	public MeshyogPrintWritter append(char paramChar) {
		write(paramChar);
		return this;
	}


}

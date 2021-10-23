package com.gopro.bene;

public class VaildationResponse {
	private boolean isValid;
	private int errorCode;
	private String errorMessaage;
	private String moduleName;

	public VaildationResponse() {
	}

	public VaildationResponse(boolean isValid, int errorCode, String errorMessaage, String moduleName) {
		super();
		this.isValid = isValid;
		this.errorCode = errorCode;
		this.errorMessaage = errorMessaage;
		this.moduleName = moduleName;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessaage() {
		return errorMessaage;
	}

	public void setErrorMessaage(String errorMessaage) {
		this.errorMessaage = errorMessaage;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Override
	public String toString() {
		return "VaildationResponse [isValid=" + isValid + ", errorCode=" + errorCode + ", "
				+ (errorMessaage != null ? "errorMessaage=" + errorMessaage + ", " : "")
				+ (moduleName != null ? "moduleName=" + moduleName : "") + "]";
	}

}

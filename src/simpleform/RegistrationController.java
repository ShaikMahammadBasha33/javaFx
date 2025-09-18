package simpleform;

import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;

public class RegistrationController {

	@FXML
	private TextField txtFullName;

	@FXML
	private TextField txtEmail;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private PasswordField txtConfirmPassword;

	@FXML
	private RadioButton rbMale;

	@FXML
	private RadioButton rbFemale;

	@FXML
	private RadioButton rbOther;

	@FXML
	private ToggleGroup genderGroup;

	@FXML
	private TextField txtPhone;

	@FXML
	private Label lblFullNameError;

	@FXML
	private Label lblEmailError;

	@FXML
	private Label lblPasswordError;

	@FXML
	private Label lblConfirmPasswordError;

	@FXML
	private Label lblGenderError;

	@FXML
	private Label lblPhoneError;

	@FXML
	private Label lblStatus;

	@FXML
	private Button btnSubmit;

	public void setTxtFullName(TextField txtFullName) {
		this.txtFullName = txtFullName;
	}

	public void setTxtEmail(TextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public void setTxtPassword(PasswordField txtPassword) {
		this.txtPassword = txtPassword;
	}

	public void setTxtConfirmPassword(PasswordField txtConfirmPassword) {
		this.txtConfirmPassword = txtConfirmPassword;
	}

	public void setTxtPhone(TextField txtPhone) {
		this.txtPhone = txtPhone;
	}

	public void setLblFullNameError(Label lblFullNameError) {
		this.lblFullNameError = lblFullNameError;
	}

	public void setLblEmailError(Label lblEmailError) {
		this.lblEmailError = lblEmailError;
	}

	public void setLblPasswordError(Label lblPasswordError) {
		this.lblPasswordError = lblPasswordError;
	}

	public void setLblConfirmPasswordError(Label lblConfirmPasswordError) {
		this.lblConfirmPasswordError = lblConfirmPasswordError;
	}

	public void setLblPhoneError(Label lblPhoneError) {
		this.lblPhoneError = lblPhoneError;
	}

	public void setLblGenderError(Label lblGenderError) {
		this.lblGenderError = lblGenderError;
	}

	public void setLblStatus(Label lblStatus) {
		this.lblStatus = lblStatus;
	}

	public void setRbMale(RadioButton rbMale) {
		this.rbMale = rbMale;
	}

	public void setRbFemale(RadioButton rbFemale) {
		this.rbFemale = rbFemale;
	}

	public void setRbOther(RadioButton rbOther) {
		this.rbOther = rbOther;
	}

	public void setGenderGroup(ToggleGroup genderGroup) {
		this.genderGroup = genderGroup;
	}

	public TextField getTxtFullName() {
		return txtFullName;
	}

	public TextField getTxtEmail() {
		return txtEmail;
	}

	public PasswordField getTxtPassword() {
		return txtPassword;
	}

	public PasswordField getTxtConfirmPassword() {
		return txtConfirmPassword;
	}

	public TextField getTxtPhone() {
		return txtPhone;
	}

	public Label getLblFullNameError() {
		return lblFullNameError;
	}

	public Label getLblEmailError() {
		return lblEmailError;
	}

	public Label getLblPasswordError() {
		return lblPasswordError;
	}

	public Label getLblConfirmPasswordError() {
		return lblConfirmPasswordError;
	}

	public Label getLblPhoneError() {
		return lblPhoneError;
	}

	public Label getLblGenderError() {
		return lblGenderError;
	}

	public Label getLblStatus() {
		return lblStatus;
	}

	public RadioButton getRbMale() {
		return rbMale;
	}

	public RadioButton getRbFemale() {
		return rbFemale;
	}

	public RadioButton getRbOther() {
		return rbOther;
	}

	@FXML
	public void initialize() {
		// Initialize Gender ToggleGroup
		genderGroup = new ToggleGroup();
		rbMale.setToggleGroup(genderGroup);
		rbFemale.setToggleGroup(genderGroup);
		rbOther.setToggleGroup(genderGroup);
		btnSubmit.disableProperty()
				.bind(txtFullName.textProperty().isEmpty().or(txtEmail.textProperty().isEmpty())
						.or(txtPassword.textProperty().isEmpty()).or(txtConfirmPassword.textProperty().isEmpty())
						.or(txtPhone.textProperty().isEmpty()).or(genderGroup.selectedToggleProperty().isNull()));

		// Add listeners for validation on each field when it loses focus
		txtFullName.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal)
				validateFullName();
		});

		txtEmail.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal)
				validateEmail();
		});

		txtPassword.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal)
				validatePassword();
		});

		txtConfirmPassword.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal)
				validateConfirmPassword();
		});

		txtPhone.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal)
				validatePhone();
		});
	}

	@FXML
	public void handleSubmit() {
		boolean isValid = true;

		validateFullName();
		validateEmail();
		validatePassword();
		validateConfirmPassword();
		validatePhone();

		isValid = validateGender() && isValid;

		// Check if any error labels have been populated
		if (!lblFullNameError.getText().isEmpty())
			isValid = false;
		if (!lblEmailError.getText().isEmpty())
			isValid = false;
		if (!lblPasswordError.getText().isEmpty())
			isValid = false;
		if (!lblConfirmPasswordError.getText().isEmpty())
			isValid = false;
		if (!lblPhoneError.getText().isEmpty())
			isValid = false;

		// Take appropriate action based on validation results
		if (isValid) {
			// Get user input values
			String fullName = txtFullName.getText();
			String email = txtEmail.getText();
			String password = txtPassword.getText();
			String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
			String phone = txtPhone.getText();

			// Construct a success message
			lblStatus.setText("Form submitted successfully!");

			// (Optional) Print the user data to the console (for demonstration)
			System.out.println("Form Submitted:");
			System.out.println("Full Name : " + fullName);
			System.out.println("Email     : " + email);
			System.out.println("Gender    : " + gender);
			System.out.println("Phone     : " + phone);
		} else {
			// Show a failure message if validations fail
			lblStatus.setText("Form submission failed. Please correct the errors.");
		}
	}

	public void validateFullName() {
		if (txtFullName.getText().trim().isEmpty()) {
			lblFullNameError.setText("Full name is required.");
		} else {
			lblFullNameError.setText("");
		}
	}

	public void validateEmail() {
		String email = txtEmail.getText();
		if (email.trim().isEmpty()) {
			lblEmailError.setText("Email is required.");
		} else if (!email.matches("^\\S+@gmail\\.com$")) {
			lblEmailError.setText("Must be a valid Gmail address.");
		} else {
			lblEmailError.setText("");
		}
	}

	public void validatePassword() {
		String password = txtPassword.getText();
		if (password.trim().isEmpty()) {
			lblPasswordError.setText("Password is required.");
		} else if (password.length() < 6) {
			lblPasswordError.setText("Password must be at least 6 characters.");
		} else {
			lblPasswordError.setText("");
		}
	}

	public void validateConfirmPassword() {
		String password = txtPassword.getText();
		String confirmPassword = txtConfirmPassword.getText();
		if (confirmPassword.trim().isEmpty()) {
			lblConfirmPasswordError.setText("Confirm password is required.");
		} else if (!confirmPassword.equals(password)) {
			lblConfirmPasswordError.setText("Passwords do not match.");
		} else {
			lblConfirmPasswordError.setText("");
		}
	}

	public void validatePhone() {
		String phone = txtPhone.getText();
		if (phone.trim().isEmpty()) {
			lblPhoneError.setText("Phone number is required.");
		} else if (!phone.matches("\\d+")) {
			lblPhoneError.setText("Phone number must contain only digits.");
		} else {
			lblPhoneError.setText("");
		}
	}

	public boolean validateGender() {
		if (genderGroup.getSelectedToggle() == null) {
			lblGenderError.setText("Gender selection is required.");
			return false;
		} else {
			lblGenderError.setText("");
			return true;
		}
	}

	@FXML
	public void handleClear() {
		clearForm();
		lblStatus.setText("Form cleared.");
	}

	@FXML
	public void handleClose() {
		Window window = txtFullName.getScene().getWindow();
		window.hide();
	}

	@FXML
	public void handleAbout() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("Registration Form");
		alert.setContentText("This form validates user input using JavaFX listeners.");
		alert.showAndWait();
	}

	public void clearForm() {
		txtFullName.clear();
		txtEmail.clear();
		txtPassword.clear();
		txtConfirmPassword.clear();
		genderGroup.selectToggle(null);
		txtPhone.clear();

		lblFullNameError.setText("");
		lblEmailError.setText("");
		lblPasswordError.setText("");
		lblConfirmPasswordError.setText("");
		lblGenderError.setText("");
		lblPhoneError.setText("");
	}
}
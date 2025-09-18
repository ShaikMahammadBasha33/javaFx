package simpleform.simpleform.test;

import javafx.application.Platform;
import javafx.scene.control.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import simpleform.RegistrationController;

public class RegistrationControllerTest {

    private RegistrationController controller;

    @BeforeClass
    public void initToolkit() {
        Platform.startup(() -> {});
    }

    @BeforeMethod
    public void setUp() {
        // Instantiate the controller
        controller = new RegistrationController();

                controller.setTxtFullName(new TextField());
        controller.setTxtEmail(new TextField());
        controller.setTxtPassword(new PasswordField());
        controller.setTxtConfirmPassword(new PasswordField());
        controller.setTxtPhone(new TextField());

        RadioButton rbMale = new RadioButton("Male");
        RadioButton rbFemale = new RadioButton("Female");
        RadioButton rbOther = new RadioButton("Other");
        ToggleGroup genderGroup = new ToggleGroup();
        rbMale.setToggleGroup(genderGroup);
        rbFemale.setToggleGroup(genderGroup);
        rbOther.setToggleGroup(genderGroup);

        controller.setRbMale(rbMale);
        controller.setRbFemale(rbFemale);
        controller.setRbOther(rbOther);
        controller.setGenderGroup(genderGroup);

        controller.setLblFullNameError(new Label());
        controller.setLblEmailError(new Label());
        controller.setLblPasswordError(new Label());
        controller.setLblConfirmPasswordError(new Label());
        controller.setLblPhoneError(new Label());
        controller.setLblGenderError(new Label());
        controller.setLblStatus(new Label());
    }

    // Test Case 1: Full Name Validation
    @Test
    public void testValidateFullName_EmptyName() {
        controller.getTxtFullName().setText(""); // Simulate empty input
        controller.validateFullName();
        Assert.assertEquals(controller.getLblFullNameError().getText(), "Full name is required.");
    }

    @Test
    public void testValidateFullName_ValidName() {
        controller.getTxtFullName().setText("Jane Doe");
        controller.validateFullName();
        Assert.assertTrue(controller.getLblFullNameError().getText().isEmpty());
    }

    // Test Case 2: Email Validation
    @Test
    public void testValidateEmail_EmptyEmail() {
        controller.getTxtEmail().setText(""); // Simulate empty email
        controller.validateEmail();
        Assert.assertEquals(controller.getLblEmailError().getText(), "Email is required.");
    }

    @Test
    public void testValidateEmail_InvalidEmailFormat() {
        controller.getTxtEmail().setText("invalid-email.com"); // Simulate invalid email
        controller.validateEmail();
        Assert.assertEquals(controller.getLblEmailError().getText(), "Must be a valid Gmail address.");
    }

    @Test
    public void testValidateEmail_ValidEmail() {
        controller.getTxtEmail().setText("example@gmail.com");
        controller.validateEmail();
        Assert.assertTrue(controller.getLblEmailError().getText().isEmpty());
    }

    // Test Case 3: Password Validation
    @Test
    public void testValidatePassword_EmptyPassword() {
        controller.getTxtPassword().setText(""); // Empty password
        controller.validatePassword();
        Assert.assertEquals(controller.getLblPasswordError().getText(), "Password is required.");
    }

    @Test
    public void testValidatePassword_TooShort() {
        controller.getTxtPassword().setText("123"); // Password less than 6 characters
        controller.validatePassword();
        Assert.assertEquals(controller.getLblPasswordError().getText(), "Password must be at least 6 characters.");
    }

    @Test
    public void testValidatePassword_ValidPassword() {
        controller.getTxtPassword().setText("mypassword"); // Valid password
        controller.validatePassword();
        Assert.assertTrue(controller.getLblPasswordError().getText().isEmpty());
    }

    // Test Case 4: Confirm Password Validation
    @Test
    public void testValidateConfirmPassword_EmptyConfirmPassword() {
        controller.getTxtPassword().setText("mypassword");
        controller.getTxtConfirmPassword().setText(""); // Empty confirm password
        controller.validateConfirmPassword();
        Assert.assertEquals(controller.getLblConfirmPasswordError().getText(), "Confirm password is required.");
    }

    @Test
    public void testValidateConfirmPassword_PasswordMismatch() {
        controller.getTxtPassword().setText("mypassword");
        controller.getTxtConfirmPassword().setText("differentpassword"); // Mismatch
        controller.validateConfirmPassword();
        Assert.assertEquals(controller.getLblConfirmPasswordError().getText(), "Passwords do not match.");
    }

    @Test
    public void testValidateConfirmPassword_MatchingPasswords() {
        controller.getTxtPassword().setText("mypassword");
        controller.getTxtConfirmPassword().setText("mypassword"); // Match
        controller.validateConfirmPassword();
        Assert.assertTrue(controller.getLblConfirmPasswordError().getText().isEmpty());
    }

    // Test Case 5: Phone Validation
    @Test
    public void testValidatePhone_EmptyPhone() {
        controller.getTxtPhone().setText(""); // Empty phone number
        controller.validatePhone();
        Assert.assertEquals(controller.getLblPhoneError().getText(), "Phone number is required.");
    }

    @Test
    public void testValidatePhone_InvalidPhoneFormat() {
        controller.getTxtPhone().setText("abc123"); // Alphanumeric phone number
        controller.validatePhone();
        Assert.assertEquals(controller.getLblPhoneError().getText(), "Phone number must contain only digits.");
    }

    @Test
    public void testValidatePhone_ValidPhone() {
        controller.getTxtPhone().setText("1234567890"); // Valid phone number
        controller.validatePhone();
        Assert.assertTrue(controller.getLblPhoneError().getText().isEmpty());
    }
}
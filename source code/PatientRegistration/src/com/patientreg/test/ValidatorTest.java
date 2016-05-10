package com.patientreg.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.patientreg.modal.Validator;

public class ValidatorTest {

	@Test
	public void testValidateData() {
		assertEquals("fname is not working fine", "First Name must not be empty.", Validator.validateData("", "mname", "lname", "20", "male", "lkdlk dljldkj", "9988217157"));
		assertEquals("fname is not working fine", "First Name must consist of only alphabets.", Validator.validateData("kdjldj&339 3", "mname", "lname", "20", "male", "lkdlk dljldkj", "9988217157"));
		assertEquals("mname is not working fine", "Middle Name must consist of only alphabets.", Validator.validateData("fname", "dljkd738$", "lname", "20", "male", "lkdlk dljldkj", "9988217157"));
		assertEquals("lname is not working fine", "Last Name must consist of only alphabets.", Validator.validateData("fname", "mname", "dljkd738$", "20", "male", "lkdlk dljldkj", "9988217157"));
		assertEquals("age is not working fine", "Age must not be less than 1. Check date of birth.", Validator.validateData("fname", "mname", "lname", "0", "male", "lkdlk dljldkj", "9988217157"));
		assertEquals("age is not working fine", "Age must not be more than 100. Check date of birth.", Validator.validateData("fname", "mname", "lname", "200", "male", "lkdlk dljldkj", "9988217157"));
		assertEquals("gender is not working fine", "Gender must be male or female.", Validator.validateData("fname", "mname", "lname", "20", "anyone", "lkdlk dljldkj", "9988217157"));
		assertEquals("address is not working fine", "Address must not be empty.", Validator.validateData("fname", "mname", "lname", "20", "male", "", "9988217157"));
		assertEquals("address is not working fine", "Address must consist of only alphabets, numeric digits, and special charaters like /,\\,#, ,;,-", Validator.validateData("fname", "mname", "lname", "20", "male", "dlkjlsj%$#@ ", "9988217157"));
		assertEquals("phone is not working fine", "Phone must not be empty.", Validator.validateData("fname", "mname", "lname", "20", "male", "ldkjldffd jh", ""));
		assertEquals("phone is not working fine", "Phone must contain exactly 10 digits.", Validator.validateData("fname", "mname", "lname", "20", "male", "ldkjldffd jh", "989898"));
		assertEquals("phone is not working fine", "Phone number must have only numeric characters and must not have all zeros or all single numbers or any sequence numbers like 0123456789.", Validator.validateData("fname", "mname", "lname", "20", "male", "ldkjldffd jh", "989898hgfd"));
		assertEquals("phone is not working fine", "Phone number must have only numeric characters and must not have all zeros or all single numbers or any sequence numbers like 0123456789.", Validator.validateData("fname", "mname", "lname", "20", "male", "ldkjldffd jh", "0123456789"));
		assertEquals("some new problem", "no", Validator.validateData("fname", "mname", "lname", "20", "male", "lkdlk dljldkj", "9988217157"));
		
	}
}

$(document).ready(function() {


    // methods added to default validator to enhance its functionality.

    // regex method added for validation using regex pattern.
    $.validator.addMethod(
        "regex",
        function(value, element, regexp)
        {
            if (regexp.constructor != RegExp)
                regexp = new RegExp(regexp);
            else if (regexp.global)
                regexp.lastIndex = 0;
            return this.optional(element) || regexp.test(value);
        },
        "Please check your input."
    );



    // age method added for calculating age and validating dob base on age.
    $.validator.addMethod(
        "age",
        function(value, element, temp)
        {
            var dateArray = value.split("/");
            var dd = parseInt(dateArray[0]);
            var mm = parseInt(dateArray[1]);
            var yy = parseInt(dateArray[2]);
            var dob = new Date(yy, mm-1, dd, 0, 0, 0, 0);


            var today = new Date();
            var age = today.getFullYear() - dob.getFullYear();
            var m = today.getMonth() - dob.getMonth();
            if (m < 0 || (m === 0 && today.getDate() < dob.getDate())) {
                age--;
            }

            var ageElement = $("#age");

            var flag = age >= 1 && age <= 100;

            if(age < 1)
                ageElement.text("age<1");
            else if(age>100)
                ageElement.text("age>100");
            else
                ageElement.text(age);
            return this.optional(element) || flag;
        },
        "Age must be in range of 1 to 100 years. Please update date of birth."
    );

    // final call to validate() function which actually validate the form.
    // List of parameters are configured to override default behaviour.
    $('#register-patient').validate(
    {
        validClass: "valid",
        errorClass: "has-error",

        rules:
        {

            address:
            {
              required: true,
              maxlength: 300,
              regex: /^[ A-Za-z0-9\n/\\#,;-]*$/,
            },

            phone:
            {
              required: true,
              maxlength: 10,
              regex: /^(?!(\d)\1{9})(?!0123456789|1234567890|0987654321|9876543210)\d{10}$/,
            },

            f_name:
            {
                required: true,
                maxlength: 100,
                regex: /^[A-Za-z]{0,100}$/,
            },

            m_name:
            {
                maxlength: 100,
                regex: /^[A-Za-z]{0,100}$/,
            },

            l_name:
            {
                maxlength: 100,
                regex: /^[A-Za-z]{0,100}$/,
            },

            dob:
            {
                required: true,
                regex: /(^(((0[1-9]|1[0-9]|2[0-8])[\/](0[1-9]|1[012]))|((29|30|31)[\/](0[13578]|1[02]))|((29|30)[\/](0[4,6,9]|11)))[\/](19|[2-9][0-9])\d\d$)|(^29[\/]02[\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)/,
                age: true,
            },
            file:
        	{
            	required: true,
            	regex: /\.(jpe?g)$/i
        	}

        },
        messages:
        {
            address:
            {
                required: 'You must enter Address.',
                regex: 'Only alpha numeric and special characters "/,\\,#, ;,-" are allowed.',
                maxlength: 'Length must not exceed 300 characters.'
            },

            phone:
            {
                required: 'You must enter Phone No.',
                maxlength: 'Only 10 digit number is allowed',
                regex: 'Phone number must have only numeric characters and must not have all zeros or all single numbers or any sequence numbers like 0123456789.'
            },


            f_name:
            {
                required: 'You must enter First Name.',
                regex: "First Name must consist of only alphabets.",
                maxlength: "Length must not exceed 100 characters."
            },

            m_name:
            {
                regex: "Middle Name must consist of only alphabets.",
                maxlength: "Length must not exceed 100 characters."
            },

            l_name:
            {
                regex: "Last Name must consist of only alphabets.",
                maxlength: "Length must not exceed 100 characters."
            },

            dob:
            {
                required: 'You must enter Date of birth.',
                regex: "It is not a valid date of birth. Please follow date pattern and age limit.",
            },
        },
    });
});

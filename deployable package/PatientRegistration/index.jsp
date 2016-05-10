<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" type="text/css" href="css/style-reset.css" />
    <link rel="stylesheet" type="text/css" href="css/my-style.css" />
</head>
<body>

    <br><br><br>
    <header>
        <hgroup>
            <h1>Patient Registration Form</h1>
        </hgroup>
        <aside>
            * Denotes Requried Fields
        </aside>
    </header>
    <section>
    <br>
    <hr>
    <%
		String error = (String) request.getAttribute("error");
		if(error!=null){
			out.write(error);
			out.write("<br><hr>");
		}
	%>
    <br>
        <form id="register-patient" method="post" action="./" enctype="multipart/form-data">

        <table>
            <tr>
                <td>
                    <label for="f_name"><span style="color: red;">*</span>First Name: </label>
                </td>
                <td>
                    <input type="text" name="f_name" /><br />
                    <label for="f_name" class="has-error"></label><br />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="m_name">&nbsp;Middle Name: </label>
                </td>
                <td>
                    <input type="text" name="m_name" /><br />
                    <label for="m_name" class="has-error"></label><br />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="l_name">&nbsp;Last Name: </label>
                </td>
                <td>
                    <input type="text" name="l_name" /><br />
                    <label for="l_name" class="has-error"></label><br />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="dob"><span style="color: red;">*</span>Date of Birth: </label>
                </td>
                <td>
                    <input type="text" placeholder="DD/MM/YYYY" name="dob" /><label>&nbsp; &nbsp;Date Format: DD/MM/YYYY eg. 04/12/1992</label><br />
                    <label for="dob" class="has-error"></label><br />
                </td>
            </tr>
            <tr>
                <td>
                    <label>&nbsp;Age: </label>
                </td>
                <td>
                    <label id="age">0</label><br />
                </td>
            </tr>

             <tr>
                <td>
                    <label for="gender"><span style="color: red;">*</span>Gender </label>
                </td>
                <td>
                    male: <input type="radio" name="gender" value="male" checked />&nbsp; female: <input type="radio" name="gender" value="female" /><br />
                    <label for="gender" class="has-error"></label><br />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="address"><span style="color: red;">*</span>Address: </label>
                </td>
                <td>
                    <textarea name="address"></textarea><br />
                    <label for="address" class="has-error"></label><br />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="phone"><span style="color: red;">*</span>Phone Number: </label>
                </td>
                <td>
                    <input type="text" name="phone" /><label>&nbsp; &nbsp;Phone No Format: 10 digit valid phone number eg. 9917364532</label><br />
                    <label for="phone" class="has-error"></label><br />
                </td>
            </tr>

            <tr>
                <td>
                    <label for="specs"><span style="color: red;">*</span>Care Specialization: </label>
                </td>
                <td>
                  <select name="specs">
                    <option value="diabetics">Diabetics</option>
                    <option value="cardiac">Cardiac</option>
                    <option value="gen-physician">General physician</option>
                    <option value="gynaecology">Gynaecology</option>
                    <option value="other">Other</option>
                  </select>
                </td>
            </tr>
             <tr>
                <td>
                    <label for="file"><span style="color: red;">*</span>Upload Image: </label>
                </td>
                <td>
                  <input type="file" name="file" /><br />
                  <label for="file" class="has-error"></label><br />
                </td>
            </tr>
        </table>
        <br />
        <input type="submit" value="Submit" />
        </form>
    </section>
 
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script src="js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="js/my-script.js" type="text/javascript"></script>
</body>
</html>
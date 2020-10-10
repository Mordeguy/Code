/* *****************************************************************************************************************
 *        Name: 2fa_email.js  
 * 
 *      Author: Nathan M. Abbey
 * 
 *     Purpose: Uses HTML + in-line CSS to create a more interactive on-boarding email explaining the company's
 *              use of 2FA. A node module is used to send the email. The username is taken from the command line.
 *      
 *         Use: node 2fa_email.js username
 * 
 *          
 * *****************************************************************************************************************
 */
var username = process.argv[2];


// These variables should be in a seperate priavte folder ---
var accountEmail = '';
var password = '';


// Personalizes the email for the specific agency ---
var companyLogoLink = '';
var companyName = 'Generic Company';


// TO and FROM - TO takes the username fromt he command line ---
var sendTo = username + '@genericcompany.com';
var from = 'email@genericompany.com'
var pathToDocx = '../../Instructions/2FA_Instructions.docx' 


// HTML Portion ----
var header = `
<header style=" width: 100%; height:auto; padding: 10px 0 10px; background-image: url(https://images.pexels.com/photos/1229861/pexels-photo-1229861.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500); text-align: center;">
    <div class="container">
        <h1 style="color:white">` + companyName + `: 2-Factor Authentication</h1>
        <h2 style="color:white">2-factor-authentication helps keep us secure.</h2>
    </div>
</header>`


var tfaInfo = `
<section id="tfaInfo"; style="padding: 10px 40px;">
    <div class="container">
        <div class="row">
        <br>
            <div>
                <h2>2-Factor Authentication</h2>
                <br>
                <h3>2-factor authentication is another added level of security. Office 365 user accounts require password and approval via an app on your mobile phone.</h3>
                <br>
                <br>
            </div>
         </div>
     </div>
</section>`


var msAuthenticatorInfo = `
<section id="msAuthenticatorInfo"; style="padding: 10px 40px; background:#f7fafc">
    <div class="container">
        <div class="row">
        <br>
            <div>
                <h2>Microsoft Authenticator App</h2>
                <br>
                <br>
                <img style = "display:block; margin-left:auto; margin-right:auto; width: 130px; padding-bottom: -20px;" src="https://www.techadvisor.co.uk/cmsdata/features/3658825/how-to-use-microsoft-authenticator-main_thumb1200_16-9.jpg" alt="i-Sight logo">
                <h3>Please download the <a href="https://www.microsoft.com/en-us/account/authenticator">Microsoft Authenticator App</a> (from the app store) on your phone.
                </h3>
            </div>
        </div>
        <ul>
            <li>When signing in to your Office 365 account, you will receive an approval request on your phone using the <b>Microsoft Authenticator App</b></li><li>This will help prevent un-authrorized access to your account.
            </li>
            <li>Please follow the attached instructions on how to download the app, and set it up correctly.
            </li>
        </ul>
        <br>
    </div>
</section>`


var footer = `
<br><br>
<footer class="py-5 bg-dark" style="text-align:center;"">
    <div class="container">
        <img style = "display:block; margin-left:auto; margin-right:auto;" src="` + companyLogoLink + ` alt="Company logo"/>
        <br>
    </div>
</footer>
<br><br>`



// Node module that uses Outlook to send email ---
// NOTE: Required app password if 2FA is activated

var nodeoutlook = require('nodejs-nodemailer-outlook')
nodeoutlook.sendEmail({
    auth: {
        user: accountEmail,
        pass: password
    },
    from: from,
    to: sendTo,
    subject: companyName + ' : 2-Factor-Authentication (2FA) Activation',
    html: header + tfaInfo + msAuthenticatorInfo + footer,
    replyTo: from,
    attachments: [ { 
                    // Sets up MS Word doc to send w/ instructions, needs to be un-commented ---
                        //filename: '2-Factor Authentication Instructions.docx',
                      //  path: pathToDocx // stream this file
                    }
                ],
    onError: (e) => console.log(e),
    onSuccess: (i) => console.log(i)
    }
 
);

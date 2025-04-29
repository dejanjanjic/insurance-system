# Insurance Company Information System

This project was developed as part of the **Internet Security** course at the Faculty of Electrical Engineering, Banja Luka, Department of Computer Science and Informatics. The goal of the project is to implement a secure information system for an insurance company.

## Project Description

The project involves the development of a system that allows clients to browse, purchase, and manage insurance policies while enabling administrators to manage user accounts and offered services. The system consists of two applications:

1. **Administrator Application**:

   - Managing user accounts.
   - Managing available insurance services.

2. **Client Application**:
   - User registration and login with two-factor authentication (username/password and verification code sent via email).
   - Browsing and purchasing insurance policies (life, travel, property insurance, etc.).
   - Receiving purchased policies in PDF format via email after a successful transaction.

## Key Features

- **Two-factor authentication** for client application users.
- **Single Sign-On (SSO)** for users with access to both applications.
- **Payment system integration** (Stripe, PayPal sandbox, etc.) in test mode.
- **PDF generation** for purchased policies.
- **Logging and monitoring of security-sensitive requests** using a SIEM component.
- **Detection of malicious requests** and automatic session termination via the Access Controller.
- **System monitoring** using Nagios.

## Technologies

- **Back-end**: Spring Boot
- **Front-end**: Angular 19
- **Database**: MySQL
- **Payment systems**: Stripe/PayPal Sandbox
- **Monitoring and security**: Nagios, SIEM

## Project Structure

1. `admin-app/` - Administrator application.
2. `client-app/` - Client application.
3. `access-controller/` - Component for authentication, authorization, and session management.
4. `siem/` - Component for logging and security monitoring.
5. `docs/` - Documentation and supplementary files.

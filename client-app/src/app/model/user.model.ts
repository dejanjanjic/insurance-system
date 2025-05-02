export interface User {
  username: string;
  mail: string;
  password: string;
}

export interface LoginDTO {
  username: string;
  password: string;
}

export interface LoginResponseDTO {
  username: string;
  mail: string;
}

export interface TokenDTO {
  token: string;
}

export interface VerificationCodeDTO {
  mail: string;
  verificationCode: string;
}

export type User = {
  id: number;
  displayName: string;
  email: string;
};

export type UserRequest = {
  displayName: string;
  email: string;
  password: string;
};

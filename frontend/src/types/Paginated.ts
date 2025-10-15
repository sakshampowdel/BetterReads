export type Paginated<T> = {
  data: T[];
  page: number;
  size: number;
  totalPages: number;
  totalElements: number;
};

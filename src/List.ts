import module from './module';

export interface getByList {
  id: string;
  title: string;
}

export const getByList = async (): Promise<getByList[]> => {
  return module.getByList();
};

export interface getList {
  list: Array<{
    id: string;
    title: string;
    image: string;
    status: string;
    genre: Array<{
      id: string;
      title: string;
    }>;
  }>;
  total: number;
}

export const getList = async ({
  page = 1,
  search = '',
  genre = '',
}: {
  page?: number;
  search?: string;
  genre?: string;
}): Promise<getList> => {
  return module.getList(
    `https://westmanga.info/page/${page}/?s=${search}&post_type=manga&genre=${genre}`
  );
};

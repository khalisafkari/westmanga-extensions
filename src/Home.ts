import module from './module';

export interface HomeResults {
  id: string;
  image: string;
  hot: boolean;
  type: string;
  boxinfo: {
    tt: string;
    ttx: string;
    imdbs: string;
    progress: string;
    score: string;
    status: string;
  };
  fixyear: {
    title: string;
    time: string;
    last_id: string;
    last_title: string;
  };
}

export const Home = async ({ page = 1 }: { page?: number }): Promise<any> => {
  return module.getHome(page);
};

export const TotalPageHome = async (): Promise<number> => {
  return module.TotalPageHome();
};

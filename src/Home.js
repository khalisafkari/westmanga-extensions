import module from './module';
export const Home = async ({ page = 1 }) => {
    return module.getHome(page);
};

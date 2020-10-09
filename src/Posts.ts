import module from './module';
import htmlConverter from './htmlConverter';

export interface getPosts {
  id: string;
  title: string;
  image: string;
  mangainfo: {
    native_title: string;
    japanese_title: string;
    total_chapter: string;
    status: string;
    author: string;
    genre: Array<{
      id: string;
      title: string;
    }>
  };
  sinopsis: string;
  list: Array<{
    id: string;
    title: string;
    download: string;
    time: string;
  }>
}

export const getPosts = async (url: string): Promise<getPosts> => {
  return module.getPosts(url)
}

export interface getPostsView {
  image: string[]
  prev: string;
  next: string;
}

export const getPostsView = async (url: string): Promise<getPostsView> => {
  return module.getPostsView(url)
}

export interface getPostsViewHtml {
  html: string;
  next: string;
  prev: string;
}

export const getPostsViewHtml = async (url: string): Promise<getPostsViewHtml> => {
  try {
    const { image,next,prev } = await getPostsView(url);
    const data: getPostsViewHtml = {
      html:htmlConverter(image),
      next,
      prev
    }
    return data
  } catch (e) {
      throw new Error(e)
  }
}

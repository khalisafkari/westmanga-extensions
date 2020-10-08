import { NativeModules } from 'react-native';

type WestmangaExtensionsType = {
  multiply(): Promise<any>;
};

const { WestmangaExtensions } = NativeModules;

export default WestmangaExtensions as WestmangaExtensionsType;

import * as React from 'react';
import { View } from 'react-native';
import WestmangaExtensions from 'westmanga-extensions';

export default function App() {

  React.useEffect(() => {
    WestmangaExtensions.multiply().then(console.log);
  }, []);

  return <View/>

}


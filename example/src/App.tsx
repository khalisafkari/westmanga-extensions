import * as React from 'react';
import { Text, View } from 'react-native';
import { getPosts } from 'westmanga-extensions';

export default function App() {

  React.useEffect(() => {
    getPosts("https://westmanga.info/manga/sono-ossan-isekai-de-nishuume-play-wo-mankitsuchuu/").then(console.log)
  },[])

  return (
    <View>
      <Text>khalis</Text>
    </View>
  )

}


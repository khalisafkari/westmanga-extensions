import * as React from 'react';
import { View } from 'react-native';
import Extensions from 'westmanga-extensions';

export default function App() {

  React.useEffect(() => {
    Extensions().then((res) => console.log(res))
  },[])

  return <View/>

}


import * as React from 'react';
import { Text, View } from 'react-native';
import { getList } from 'westmanga-extensions';

export default function App() {

  React.useEffect(() => {
    getList({page:6}).then(console.log)
  },[])

  return (
    <View>
      <Text>khalis</Text>
    </View>
  )

}


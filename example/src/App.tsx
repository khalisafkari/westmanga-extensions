import * as React from 'react';
import { Text, View } from 'react-native';
import API, { Home } from 'westmanga-extensions';

export default function App() {
  React.useEffect(() => {
    Home({})
      .then(console.log)
      .catch((e) => {
        console.log('error');
        console.log(e);
      });
    API().then(console.log).catch(console.log);
  }, []);

  return (
    <View>
      <Text>khalis</Text>
    </View>
  );
}

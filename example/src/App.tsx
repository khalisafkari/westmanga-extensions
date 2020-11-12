import * as React from 'react';
import { Text, View } from 'react-native';
import { Home } from 'westmanga-extensions';

export default function App() {
  React.useEffect(() => {
    Home({}).then(console.log);
  }, []);

  return (
    <View>
      <Text>khalis</Text>
    </View>
  );
}

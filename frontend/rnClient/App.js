/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, {useState} from 'react';
import type {Node} from 'react';
import {Button, SafeAreaView, StyleSheet, Text, View} from 'react-native';
import {WebView} from 'react-native-webview';

const App: () => Node = () => {
  const [data, setData] = useState(0);
  function onClickAdd() {
    setData(data + 1);
  }
  const webViewUrl = `https://yhuj79.github.io/SOS-Service/${data}`;
  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.div_location}>
        <Button
          onPress={onClickAdd}
          title="Add"
          color="#841584"
          accessibilityLabel="Learn more about this purple button"
        />
        <Text style={styles.title}>rnClient</Text>
      </View>
      <WebView source={{uri: webViewUrl}} />
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  div_location: {
    backgroundColor: '#FFF',
    paddingTop: 70,
    paddingBottom: 70,
  },
  title: {
    fontWeight: '700',
    fontSize: 30,
    textAlign: 'center',
    marginBottom: 30,
  },
});

export default App;

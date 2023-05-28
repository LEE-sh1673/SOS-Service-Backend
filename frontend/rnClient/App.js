/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, {useEffect, useRef} from 'react';
import {Platform, PermissionsAndroid} from 'react-native';
import type {Node} from 'react';
import {SafeAreaView, StyleSheet, Text, View} from 'react-native';
import {WebView} from 'react-native-webview';
import Geolocation from 'react-native-geolocation-service';

const App: () => Node = () => {
  const webViewRef = useRef(null);

  useEffect(() => {
    if (Platform.OS === 'ios') {
      Geolocation.requestAuthorization('always');
    } else if (Platform.OS === 'android') {
      PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION,
      );
    }
  }, []);

  useEffect(() => {
    Geolocation.getCurrentPosition(
      position => {
        const {latitude, longitude} = position.coords;
        const message = JSON.stringify({latitude, longitude});
        console.log(message);
        webViewRef.current.postMessage(message);
      },
      error => {
        console.log(error);
      },
      {enableHighAccuracy: true, timeout: 15000, maximumAge: 10000},
    );
  }, []);

  const webViewUrl = 'https://sos-service-h4nvkatdm-yhuj79.vercel.app/map';
  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.div_location}>
        <Text style={styles.title}>rnClient</Text>
      </View>
      <WebView
        ref={webViewRef}
        source={{uri: webViewUrl}}
        injectedJavaScript={`
        window.addEventListener('message', function(event) {
          console.log('Received message from RN:', event.data);
        });
      `}
      />
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

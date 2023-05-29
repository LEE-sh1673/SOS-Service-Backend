/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, {useEffect, useRef, useState} from 'react';
import {Platform, PermissionsAndroid} from 'react-native';
import type {Node} from 'react';
import {SafeAreaView, StyleSheet, Text, View} from 'react-native';
import {WebView} from 'react-native-webview';
import Geolocation from 'react-native-geolocation-service';

const App: () => Node = () => {
  const webViewRef = useRef(null);
  const [lat, setLat] = useState('');
  const [long, setLong] = useState('');

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
    const watchId = Geolocation.watchPosition(
      position => {
        const {latitude, longitude} = position.coords;
        // 위치 좌표 업데이트 후 원하는 작업 수행
        console.log('현재 위치:', latitude, longitude);
        setLat(latitude);
        setLong(longitude);
      },
      error => {
        console.log('위치 업데이트 에러:', error);
      },
      {
        enableHighAccuracy: true,
        distanceFilter: 10, // 위치 업데이트 간격 (미터)
        fastestInterval: 500, // 최소 업데이트 시간 간격 (밀리초)
      },
    );

    return () => {
      // 컴포넌트가 unmount될 때 위치 감시 해제
      Geolocation.clearWatch(watchId);
    };
  }, []);

  const webViewUrl = `https://yhuj79.github.io/SOS-Service/map/${lat}/${long}`;
  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.div_location}>
        <Text style={styles.title}>rnClient</Text>
      </View>
      <WebView ref={webViewRef} source={{uri: webViewUrl}} />
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

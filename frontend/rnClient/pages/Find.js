import React from 'react';
import {Pressable, StyleSheet, Text, View, Alert} from 'react-native';

export const Find = () => {
  return (
    <View style={styles.container}>
      <Text>로그인이 필요한 서비스입니다.</Text>
      <Pressable onPress={() => Alert.alert('로그인')} style={styles.button}>
        <Text style={styles.text}>로그인</Text>
      </Pressable>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  button: {
    width: 80,
    backgroundColor: '#B2CCFF',
    borderRadius: 12,
    padding: 10,
    margin: 25,
  },
  text: {
    textAlign: 'center',
    color: '#000',
  },
});

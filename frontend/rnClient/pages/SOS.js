import React from 'react';
import {StyleSheet, Alert, View, Pressable, Text} from 'react-native';

export const SOS = () => {
  return (
    <View>
      <Pressable onPress={() => Alert.alert('SOS')} style={styles.button}>
        <Text style={styles.text}>SOS</Text>
      </Pressable>
    </View>
  );
};

const styles = StyleSheet.create({
  button: {
    width: 80,
    backgroundColor: '#EF3636',
    borderRadius: 50,
    padding: 10,
    marginTop: 5,
  },
  text: {
    textAlign: 'center',
    margin: -6,
    color: '#FFF',
    fontSize: 23,
    fontWeight: 'bold',
  },
});

import React from 'react';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import {MyLocation} from './pages/MyLocation';
import {SOS} from './pages/SOS';
import {Find} from './pages/Find';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';

const Tab = createBottomTabNavigator();

const PayScreenComponent = () => {
  return null;
};

const TabNavigation = () => {
  return (
    <Tab.Navigator
      tabBarOptions={{
        activeTintColor: 'black',
        inactiveTintColor: '#ABABAB',
      }}>
      <Tab.Screen
        name="내 위치"
        component={MyLocation}
        options={{
          tabBarIcon: ({color, focused}) => (
            <MaterialIcons
              color={focused ? 'black' : '#ABABAB'}
              name={'location-on'}
              size={27}
            />
          ),
        }}
      />
      <Tab.Screen
        name="SOS"
        component={PayScreenComponent}
        options={{
          tabBarButton: () => <SOS />,
        }}
      />
      <Tab.Screen
        name="찾기"
        component={Find}
        options={{
          tabBarIcon: ({color, focused}) => (
            <MaterialIcons
              color={focused ? 'black' : '#ABABAB'}
              name={'person-search'}
              size={27}
            />
          ),
        }}
      />
    </Tab.Navigator>
  );
};

export default TabNavigation;

/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, {useState} from 'react';
import type {Node} from 'react';
import {
  NativeModules,
  SafeAreaView,
  ScrollView,
  StatusBar,
  Switch,
  StyleSheet,
  Text,
  DeviceEventEmitter,
  Alert,
  Button,
  useColorScheme,
  View,
} from 'react-native';
import Slider from '@react-native-community/slider';
import {
  Colors,
  DebugInstructions,
  Header,
  LearnMoreLinks,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';
const Section = ({children, title}): Node => {
  const isDarkMode = useColorScheme() === 'dark';
  return (
    <View style={styles.sectionContainer}>
      <Text
        style={[
          styles.sectionTitle,
          {
            color: isDarkMode ? Colors.white : Colors.black,
          },
        ]}>
        {title}
      </Text>
      <Text
        style={[
          styles.sectionDescription,
          {
            color: isDarkMode ? Colors.light : Colors.dark,
          },
        ]}>
        {children}
      </Text>
    </View>
  );
};

const App: () => Node = () => {
  const isDarkMode = useColorScheme() === 'dark';
  const [allinfo, setAllInfo] = useState('哇哈哈哈哈哈');
  //getAllInfo();
  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  const getAllInfo = () => {
    NativeModules.OMDeviceModule.invoke({methodName: 'allInfo'}, msg => {
      Alert.alert(JSON.stringify(msg));
      setAllInfo(msg);
      NativeModules.OMDeviceModule.toast(JSON.stringify(msg));
    });
  };

  const getDeviceMac = () => {
    NativeModules.OMDeviceModule.invoke({methodName: 'mac'}, msg => {
      Alert.alert(JSON.stringify(msg));
      setAllInfo(msg);
      NativeModules.OMDeviceModule.toast(JSON.stringify(msg));
    });
  };

  const setLCDOff = () => {
    NativeModules.OMDeviceModule.invoke({methodName: 'setLCDOff'}, msg => {
      Alert.alert(JSON.stringify(msg));
      setAllInfo(msg);
    });
  };

  const setLCDOn = () => {
    NativeModules.OMDeviceModule.invoke({methodName: 'setLCDOn'}, msg => {
      Alert.alert(JSON.stringify(msg));
      setAllInfo(msg);
    });
  };

  const gotoWiFi = () => {
    NativeModules.OMDeviceModule.invoke({methodName: 'gotoWiFi'}, msg => {
      setAllInfo(msg);
    });
  };

  const gotoLanguage = () => {
    NativeModules.OMDeviceModule.invoke({methodName: 'gotoLanguage'}, msg => {
      setAllInfo(msg);
    });
  };

  const reboot = () => {
    NativeModules.OMDeviceModule.invoke({methodName: 'reboot'}, msg => {
      setAllInfo(msg);
    });
  };

  const shutdown = () => {
    NativeModules.OMDeviceModule.invoke({methodName: 'shutdown'}, msg => {
      setAllInfo(msg);
    });
  };

  const setNavigationBarVisibility = value => {
    NativeModules.OMDeviceModule.invoke(
      {methodName: 'setNavigationBarVisibility', status: value},
      msg => {
        setAllInfo(msg);
      },
    );
  };

  const setStatusBarVisibility = value => {
    NativeModules.OMDeviceModule.invoke(
      {methodName: 'setStatusBarVisibility', status: value},
      msg => {
        setAllInfo(msg);
      },
    );
  };

  const setSysTime = value => {
    NativeModules.OMDeviceModule.invoke(
      {methodName: 'setSysTime', status: value},
      msg => {
        setAllInfo(msg);
      },
    );
  };

  const setScreenBrightness = value => {
    NativeModules.OMDeviceModule.invoke(
      {methodName: 'setScreenBrightness', brightness: value},
      msg => {
        //NativeModules.OMDeviceModule.toast(msg.toString());
        setAllInfo(msg);
      },
    );
  };

  const setVolume = value => {
    NativeModules.OMDeviceModule.invoke(
      {methodName: 'setVolume', volume: value},
      msg => {
        setAllInfo(msg);
      },
    );
  };
  const setRotation = value => {
    NativeModules.OMDeviceModule.invoke(
      {methodName: 'setRotation', degree: value},
      msg => {
        setAllInfo(msg);
      },
    );
  };

  const setOnOffTime = (
    year1,
    month1,
    day1,
    hour1,
    minute1,
    year2,
    month2,
    day2,
    hour2,
    minute2,
  ) => {
    NativeModules.OMDeviceModule.invoke(
      {
        methodName: 'setOnOffTime',
        on_year: year1,
        on_month: month1,
        on_day: day1,
        on_hour: hour1,
        on_minute: minute1,
        off_year: year2,
        off_month: month2,
        off_day: day2,
        off_hour: hour2,
        off_minute: minute2,
      },
      result => {
        console.log(result);
      },
    );
  };

  return (
    <View style={styles.container}>
      <View style={styles.row}>
        <Text style={styles.welcome}>亮度调节：</Text>
        <Slider
          style={{width: 400, height: 60}}
          minimumValue={0}
          value={allinfo.brightness}
          maximumValue={100}
          minimumTrackTintColor={'red'}
          maximumTrackTintColor={'green'}
          onValueChange={value => {
            setScreenBrightness(value);
          }}
        />
      </View>

      <View style={styles.row}>
        <Text style={styles.welcome}>音量调节：</Text>
        <Slider
          style={{width: 400, height: 40}}
          value={allinfo.volume}
          minimumValue={0}
          maximumValue={100}
          minimumTrackTintColor={'red'}
          maximumTrackTintColor={'green'}
          onValueChange={value => {
            setVolume(value);
          }}
        />
      </View>
      <View style={styles.row}>
        <Button
          style={styles.welcome}
          title="获取Mac地址"
          onPress={getDeviceMac}
        />
        <Button
          style={styles.welcome}
          title="获取设备信息"
          onPress={getAllInfo}
        />
      </View>
{/*

      <View style={styles.row}>
        <Button
          title="0"
          onPress={() => {
            setRotation('0');
          }}
        />
        <Button
          title="90"
          onPress={() => {
            setRotation('90');
          }}
        />
        <Button
          title="180"
          onPress={() => {
            setRotation('180');
          }}
        />
        <Button
          title="270"
          onPress={() => {
            setRotation('270');
          }}
        />
      </View>

      <View style={styles.row}>
        <Button
          style={styles.welcome}
          title="状态栏开"
          onPress={() => {
            setStatusBarVisibility(true);
          }}
        />
        <Button
          style={styles.welcome}
          title="状态栏关"
          onPress={() => {
            setStatusBarVisibility(false);
          }}
        />
      </View>


      <View style={styles.row}>
        <Button
          style={styles.welcome}
          title="自动获取时间-开"
          onPress={() => {
            setSysTime(true);
          }}
        />
        <Button
          style={styles.welcome}
          title="自动获取时间-关"
          onPress={() => {
            setSysTime(false);
          }}
        />
      </View>

      <View style={styles.row}>
        <Button
          style={styles.welcome}
          title="导航栏开"
          onPress={() => {
            setNavigationBarVisibility(true);
          }}
        />
        <Button
          style={styles.welcome}
          title="导航栏关"
          onPress={() => {
            setNavigationBarVisibility(false);
          }}
        />
      </View>
*/}

      <View style={styles.row}>
        <Button style={styles.welcome} title="开启屏背光" onPress={setLCDOn} />
        <Button style={styles.welcome} title="关闭屏背光" onPress={setLCDOff} />
      </View>
      <View style={styles.row}>
        <Button style={styles.welcome} title="关机" onPress={shutdown} />
        <Button style={styles.welcome} title="重启" onPress={reboot} />
      </View>
      <View style={styles.row}>
        <Button style={styles.welcome} title="设置WiFi" onPress={gotoWiFi} />
        <Button
          style={styles.welcome}
          title="设置语言"
          onPress={gotoLanguage}
        />
      </View>

      <Text style={styles.instructions}>{JSON.stringify(allinfo)} </Text>
      <Text style={styles.instructions}>
        Double tap R on your keyboard to reload,{'\n'}
        Shake or press menu button for dev menu
      </Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  row: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    marginTop: 10,
    marginLeft: 5,
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
    marginLeft: 10,
    left: 10,
  },
  instructions: {
    marginTop: 10,
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

export default App;

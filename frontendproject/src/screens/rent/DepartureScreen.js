import React, { Component } from "react";
import { StyleSheet,Vibration, Text, View, Button, AppRegistry } from "react-native";
import * as Permissions from "expo-permissions";
import axios from "axios"
import { BarCodeScanner } from "expo-barcode-scanner";
import {ROOT_URL} from '../../url/url';

class DepartureScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
      hasCameraPermission: null,
      scanned: false,
      bikeNum:0,
      user:this.props.navigation.state.params.user,
      bicycleNumber: false
    };
  }

  async componentDidMount() {

    this.getPermissiongsAsync();
  }
  getPermissiongsAsync = async () => {
    const { status } = await Permissions.askAsync(Permissions.CAMERA);
    this.setState({ hasCameraPermission: status === "granted" });
  };
  handleBarCodeScanned = ({ type, data }) => {
    this.setState({ scanned: true});
    Vibration.vibrate(500)
    console.log(data);
    
    axios.get(`${ROOT_URL}/rental/rent`,{
      params:{
        bicycleNumber:data,
        userId:this.state.user
      }
    }).then(res=>{
      this.setState({bicycleNumber: data});
      alert(data + " 번 자전거의 사용을 시작합니다.");
    })
    .catch(error => {
      alert("자전거 대여가 안됐습니다. 다시 시도해주세요!!");
    });
  };
  render() {
    const { hasCameraPermission, scanned } = this.state;

    if (hasCameraPermission === null) {
      return <Text>Requesting for camera permission</Text>;
    }
    if (hasCameraPermission === false) {
      return <Text>No access to camera</Text>;
    }
    return (
      <View style={styles.container}>
        <Text style={styles.fontStyle}>근처 자전거의 QR코드를 찍어주세요!</Text>
        <BarCodeScanner
          style={{width:"100%",height:"80%"}}
          onBarCodeScanned={scanned ? undefined : this.handleBarCodeScanned}
          barCodeTypes={[BarCodeScanner.Constants.BarCodeType.qr]}
        />

        <Button
          title="Start"
          onPress={() => {
              if(!this.state.bicycleNumber){
                alert("QR코드를 먼저 인식하여 대여를 확정지어주세요!!");
              }else {
                const isLogin = {
                  "id": this.state.user,
                  "usingBicycle": this.state.bicycleNumber
                }
                console.log("isLogin 출발 : ");
                console.log(isLogin);
                this.props.navigation.state.params.onGoBack(isLogin);
                this.props.navigation.goBack();
              }
            }
          }
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor:"#303144"
  },
  fontStyle:{
    color:"white" 
  }
});

export default DepartureScreen;

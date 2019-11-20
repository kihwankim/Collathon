import React, { Component } from "react";
import { StyleSheet, Text, View,KeyboardAvoidingView, Image,  TextInput } from "react-native";
import axios from "axios";
import {Input,Button} from "react-native-elements"
import Icon from 'react-native-vector-icons/FontAwesome';
import Feather from 'react-native-vector-icons/Feather';
class LoginScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
      userId: "",
      userPw: "",
      location:this.props.navigation.state.params.location
    };
  }

  sendUserData = () => {
    if (!( this.state.userId && this.state.userPw)) {
      alert("모두 입력해주세요!");
    } else {
      axios
        .post("http://192.168.0.74:8090/user/login", {
          userId: this.state.userId,
          userPw: this.state.userPw
        })
        .then(res => {
          if(res.status===200){
            if(this.state.userId != 'Admin'){
            alert("환영합니다! " + this.state.userId)
            this.props.navigation.navigate("Home",{isLogin:res.data.data})
            } else{
              alert("Admin Page로 이동합니다.")
              this.props.navigation.navigate("Admin",{
                location:this.state.location
              })
            } 
          } 
        });
    } 
  };
  render() {
    return (
      <KeyboardAvoidingView style={{ width: "100%", height: "100%",backgroundColor:"#303144" }} behavior="height" enabled>
        <View style={styles.logo}>
          <Image
            source={require("../../assets/logo.png")}
            style={{
              width: 300,
              height: 300,
            }}
          />
        </View>
        <View style={styles.firstRow}>
          <View style={styles.secondRow}>
            <View style={styles.thirdRow}>

              <Input
                placeholder="아이디를 입력해주세요"
                onChangeText={value => this.setState({ userId: value })}
                label="ID"
                leftIcon={<Feather
                  name='user'
                  size={24}
                  color='white'
                />}
                inputStyle={{color: 'white'}}
              />
              </View>
              <View style={styles.thirdRow}>

              <Input
                label="Password"
                placeholder="비밀번호를 입력해주세요"
                secureTextEntry
                onChangeText={value => this.setState({ userPw: value })}
                leftIcon={<Feather
                  name='lock'
                  size={24}
                  color='white'
                />}
                inputStyle={{color: 'white'}}
                />
                </View>
          </View>
          <View style={styles.secondRow}>
            <View style={styles.thirdRow}>
              <Button title="로그인" buttonStyle={{width:100, backgroundColor: "#4EB8CE"}} type="outline" onPress={this.sendUserData}
               titleStyle={{color: "white"}}/>
              <Button
                title="회원가입"
                buttonStyle={{width:100,marginLeft:20, backgroundColor: "#4EB8CE"}}
                type="outline"
                onPress={() => this.props.navigation.navigate("Signup")}
                titleStyle={{color: "white"}}
              />
              
            </View>
            <View style={styles.thirdRow}></View>
          </View>
        </View>
      </KeyboardAvoidingView>
    );
  }
}

const styles = StyleSheet.create({
  logo: {
    flex: 1,
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
    height: "50%",
   
  },
  firstRow: {
    flex: 1,
    flexDirection: "column"
  },
  secondRow: {
    flex: 1,
    flexDirection: "column",
  },
  thirdRow: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
    flexDirection:"row",
   
  },
});

export default LoginScreen;
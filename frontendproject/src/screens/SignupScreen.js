import React, { Component } from "react";
import {
  StyleSheet,
  KeyboardAvoidingView,
  Image,
  Text,
  View,
  TextInput,
  Alert
} from "react-native";
import DatePicker from "react-native-datepicker";
import axios from "axios";
import { Input, Button } from "react-native-elements";
import Feather from "react-native-vector-icons/Feather";

class LoginScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
      userId: "",
      userPw: "",
      name: "",
      date: ""
    };
  }

  sendUserData = () => {
    if (!(this.state.name && this.state.userId && this.state.userPw)) {
      alert("모두 입력해주세요!");
    } else {
      const splitDate = this.state.date.split("-");
      this.state.date = new Date(
        parseInt(splitDate[0]),
        parseInt(splitDate[1]) - 1,
        parseInt(splitDate[2])
      );
      axios
        .post("http://192.168.1.109:8090/user/save", {
          name: this.state.userId,
          userId: this.state.userId,
          userPw: this.state.userPw,
          birthDate: this.state.date
        })
        .then(res => {
          if (res.status === 200) {
            alert("회원가입이 완료되었습니다.");
            this.props.navigation.navigate("Home");
          }
        })
        .catch(e => {
          alert("동일한 회원이 있습니다.");
        });
    }
  };
  render() {
    return (
      <KeyboardAvoidingView
        style={{ width: "100%", height: "100%", backgroundColor: "#303144" }}
        behavior="height"
        enabled
      >
        <View style={styles.logo}>
          <Image
            source={require("../../assets/logo.png")}
            style={{
              width: 300,
              height: 300,
              borderWidth: 1,
              borderColor: "black"
            }}
          />
        </View>
        <View style={styles.firstRow}>
          <View style={styles.thirdRow}>
            <Input
              label="name"
              placeholder="이름을 입력해주세요"
              onChangeText={value => this.setState({ name: value })}
              leftIcon={<Feather name="user-plus" size={24} color="white" />}
              inputStyle={{ color: "white" }}
            />
          </View>
          <View style={styles.thirdRow}>
            <Input
              label="ID"
              placeholder="아이디를 입력해주세요"
              onChangeText={value => this.setState({ userId: value })}
              leftIcon={<Feather name="user" size={24} color="white" />}
              inputStyle={{ color: "white" }}
            />
          </View>
          <View style={styles.thirdRow}>
            <Input
              secureTextEntry
              label="Password"
              placeholder="비밀번호를 입력해주세요"
              onChangeText={value => this.setState({ userPw: value })}
              leftIcon={<Feather name="lock" size={24} color="white" />}
              inputStyle={{ color: "white" }}
            />
          </View>
          <View style={styles.thirdRow}>
            <DatePicker
              format="YYYY-MM-DD"
              style={{ width: "100%" }}
              placeholder="생년월일을 입력해주세요"
              androidMode="spinner"
              date={this.state.date}
              onDateChange={date => this.setState({ date: date })}
              customStyles={{
                placeholderText: {
                  color: "white"
                },
                dateText: {
                  color: "white"
                }
              }}
            />
          </View>
          <View style={styles.thirdRow}>
            <Button
              title="회원가입"
              type="outline"
              onPress={this.sendUserData}
              buttonStyle={{ backgroundColor: "#4EB8CE" }}
              titleStyle={{ color: "white" }}
            />
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
    height: "50%"
  },
  firstRow: {
    flex: 1,
    flexDirection: "column"
  },
  secondRow: {
    flex: 1,
    flexDirection: "column"
  },
  thirdRow: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
    flexDirection: "row"
  }
});

export default LoginScreen;

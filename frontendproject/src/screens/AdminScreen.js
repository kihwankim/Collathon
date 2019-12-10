import React, { Component } from "react";
import axios from "axios";
import {
  StyleSheet,
  Image,
  Picker,
  Text,
  TouchableOpacity,
  View,
  ScrollView,
  AppRegistry
} from "react-native";
import { Button } from "react-native-elements";
import {ROOT_URL} from '../url/url';

class AdminScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
      location: this.props.navigation.state.params.location,
      bikeList: [],
      remove: 0,
      check:false
    };
  }
  register() {
    axios
      .post(`${ROOT_URL}/bicycle/save`, {
        longitude: this.state.location.longitude,
        latitude: this.state.location.latitude
      })
      .then(res => {
        if (res.status == 200) {
          this.setState({bikeList:res.data.data})
          alert(res.data.message + "번 등록 완료");
        } else {
          alert("등록에 실패했습니다.");
        }
      });
  }

  componentWillMount() {
    axios.get(`${ROOT_URL}/bicycle/getAll`).then(res => { 
    this.setState({ bikeList: res.data.data });
    });
  }
  delete(v) {
    axios.delete(`${ROOT_URL}/bicycle/delete/${v}`).then(res=>{
      if(res.status==200){
        this.setState({bikeList:res.data.data})
      }
    })
  }

  getBikeListOpacity() {
    return (this.state.bikeList.map((item, index) => {
      return (
        <TouchableOpacity
          key={item.bicycleNumber}
          style={styles.thirdRow}
          onPress={() => {
            this.delete(item.bicycleNumber);
          }}
        >
          <Text style={{ fontSize: 30, color: "white", textAlign: "center" }}>
            {item.bicycleNumber}
          </Text>
        </TouchableOpacity>
      );
    })
    );
  }

  render() {
    if (!this.state.bikeList) {
      return (
        <View>
          <Text>...Loading</Text>
        </View>
      );
    } else {
      var List = this.getBikeListOpacity.bind(this);
      return (
        <View
          style={{ width: "100%", height: "100%", backgroundColor: "#303144" }}
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
              <Button
                title="Register"
                buttonStyle={{ width: 100, backgroundColor: "#4EB8CE" }}
                type="outline"
                onPress={this.register.bind(this)}
              />
              <Button
                title="Delete"
                onPress={this.delete.bind(this)}
                buttonStyle={{ width: 100, backgroundColor: "#4EB8CE" }}
                type="outline"
              />
            </View>
            <ScrollView style={styles.secondRow}>
              <List/>
              
            </ScrollView>
          </View>
        </View>
      );
    }
  }
}

const styles = StyleSheet.create({
  logo: {
    flex: 1,
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
    borderWidth: 1,
    height: "50%"
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
    borderWidth: 1,
    alignItems: "center",
    justifyContent: "center",
    flexDirection: "row"
  }
});

export default AdminScreen;

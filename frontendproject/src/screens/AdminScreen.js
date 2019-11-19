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

class AdminScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
      location: this.props.navigation.state.params.location,
      bikeList: [],
      remove:0
    };
  }
  register() {
    axios
      .post("http://192.168.0.74:8090/bicycle/save", {
        longitude: this.state.location.longitude,
        latitude: this.state.location.latitude
      })
      .then(res => {
        if (res.status == 200) {
          alert(res.data.message+"번 등록 완료");
        } else {
          alert("등록에 실패했습니다.");
        }
      });
  }

  componentWillMount() {
    axios.get("http://192.168.0.74:8090/bicycle/getAll").then(res => {
      this.setState({bikeList:res.data})
    });
  }
  delete(v){
    axios.post("http://192.168.0.74:8090/bicycle")
    
    
  }
  render() {
    if(!this.state.bikeList){
        <View>
            <Text>

            ...Loading
            </Text>
        </View>
    }else{

    
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
               {
                  this.state.bikeList.map((item, index) => (
                     <TouchableOpacity key={item.bikeNum} style={styles.thirdRow} onPress={()=>{
                         this.delete(item.bikeNum)
                     }}>
                         <Text style={{fontSize:30,color:"white",textAlign:"center"}}>{item.bikeNum}</Text>
                     </TouchableOpacity>
                  ))
               }
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
    borderColor: "black",
    height: "50%"
  },
  firstRow: {
    flex: 1,
    flexDirection: "column"
  },
  secondRow: {
    flex: 1,
    flexDirection: "column",
    borderWidth: 1,
    borderColor: "black"
  },
  thirdRow: {
    flex: 1,
    borderWidth: 1,
    borderColor: "black",
    alignItems: "center",
    justifyContent: "center",
    flexDirection: "row"
  }
});

export default AdminScreen;

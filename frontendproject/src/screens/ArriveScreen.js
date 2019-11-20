import React, { Component } from "react";
import { StyleSheet, Text, View } from "react-native";
import { SkypeIndicator } from "react-native-indicators";
import axios from "axios";
import { DotIndicator } from "../components/indicators";
import { Button } from "react-native-elements";
import MapView, { Marker } from "react-native-maps";
import moment from "moment";

class ArriveScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
      location: this.props.navigation.state.params.location,
      isLoad: false,
      time: 0,
      user: this.props.navigation.state.params.user
    };
  }

  componentDidMount() {
    navigator.geolocation.getCurrentPosition(async position => {
      var geocode =
        "https://maps.googleapis.com/maps/api/geocode/json?latlng=" +
        position.coords.latitude +
        "," +
        position.coords.longitude +
        "&key=AIzaSyBgEzBTBpggPy6ouKjcIaKkNHlyAuKZ59Q";

      axios.post(geocode, {}).then(myJSONResult => {
        if (myJSONResult.status === 200) {
          myJSONResult = myJSONResult.data;
          this.setState({
            location: {
              name: myJSONResult.results[0].formatted_address,
              latitude: position.coords.latitude,
              longitude: position.coords.longitude
            },
            isLoad: true
          });
        }
      });
      setInterval(() => {
        this.setState({
          time: this.state.time + 1
        });
      }, 1000);
    });
  }
  returnBicycle() {
    console.log(this.state.user);
    axios
      .get("http://192.168.0.74:8090/rental/return", {
        params: {
          longitude: this.state.location.longitude,
          latitude: this.state.location.latitude,
          bicycleNumber: this.state.user.usingBicycle,
          userId: this.state.user.id
        }
      })
      .then(res => {
        if (res.status == 200) {
          alert("반환 완료");
        }
      });
    this.props.navigation.navigate("Home");
  }
  render() {
    if (!this.state.isLoad) {
      return (
        <View style={styles.loader}>
          <DotIndicator color="black" />
        </View>
      );
    } else {
      return (
        <View
          style={{ backgroundColor: "#303144", width: "100%", height: "100%" }}
        >
          <MapView
            initialRegion={{
              latitude: this.state.location.latitude,
              longitude: this.state.location.longitude,
              latitudeDelta: 0.0922,
              longitudeDelta: 0.0421
            }}
            style={{
              width: "90%",
              height: "60%",
              marginLeft: "5%",
              marginRight: "5%",
              marginTop: "5%"
            }}
            showsUserLocation={true}
          >
            <Marker
              coordinate={{
                latitude: this.state.location.latitude,
                longitude: this.state.location.longitude
              }}
              onPress={() =>
                this.props.navigation.navigate("Departure", {
                  location: this.state.location
                })
              }
            />
          </MapView>
          <View style={styles.firstRow}>
            <View style={styles.secondRow}>
              <Text style={styles.fontStyle}>현재 위치</Text>
              <Text style={styles.fontStyle}> {this.state.location.name}</Text>
              <Text style={styles.fontStyle}>반납하시겠습니까??</Text>
            </View>
            <View style={styles.secondRow}>
              <Text style={styles.fontStyle}>이용 누적 시간</Text>
              <Text style={styles.fontStyle}> {this.state.time}</Text>
            </View>
            <View style={styles.thirdRow}>
              <Button
                title="YES"
                type="outline"
                buttonStyle={{
                  marginRight: 30,
                  width: 100,
                  backgroundColor: "#4EB8CE"
                }}
                onPress={() => {
                  this.returnBicycle();
                }}
                titleStyle={{ color: "white" }}
              />

              <Button
                title="NO"
                type="outline"
                buttonStyle={{ width: 100, backgroundColor: "#4EB8CE" }}
                onPress={() => this.props.navigation.navigate("Running")}
                titleStyle={{ color: "white" }}
              />
            </View>
          </View>
        </View>
      );
    }
  }
}

const styles = StyleSheet.create({
  loader: {
    flex: 1,
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#222431"
  },
  firstRow: {
    flexDirection: "column"
  },
  secondRow: {
    flexDirection: "column",
    alignItems: "center"
  },
  thirdRow: {
    borderWidth: 1,
    alignItems: "center",
    justifyContent: "center",
    flexDirection: "row"
  },
  fontStyle: {
    marginBottom: 20,
    fontSize: 15,
    fontFamily: "sunflower",
    color: "white"
  }
});

export default ArriveScreen;

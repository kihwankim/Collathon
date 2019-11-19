import React, { Component } from "react";
import {
  StyleSheet,
  Text,
  View,
  Button,
  ImageBackground,
  TouchableOpacity
} from "react-native";
import axios from "axios";
import MapView, { Marker } from "react-native-maps";

class MapScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
      location: this.props.navigation.state.params.location
        ? this.props.navigation.state.params.location
        : this.props.location,
      isFont: false,
      marker:[{latitude:this.props.navigation.state.params.location.latitude,longitude:this.props.navigation.state.params.location.longitude},{latitude:36.3665383,longitude:127.3446726}]
    };
  }
  componentDidMount() {}
  makrMarker(){
    return this.state.marker.map((data)=>{
      return(
        <Marker
                coordinate={{
                  latitude: data.latitude,
                  longitude: data.longitude
                }}
                onPress={() =>
                  this.props.navigation.navigate("Departure", {
                    location: this.state.location
                  })
                }
              />
      )
    })
  }
  render() {

    console.log(this.props)
    return (
      <View style={{ backgroundColor: "#303144" }}>
        <MapView
          initialRegion={{
            latitude: this.state.location.latitude,
            longitude: this.state.location.longitude,
            latitudeDelta: 0.0322,
            longitudeDelta: 0.0151
          }}
          style={{
            width: "100%",
            height: "80%"
          }}
          showsUserLocation={true}
        >
          {
            this.makrMarker()
            // this.state.marker.map((item)=>{
            //   <Marker
            //     coordinate={{
            //       latitude: item.latitude,
            //       longitude: item.longitude
            //     }}
            //     onPress={() =>
            //       this.props.navigation.navigate("Departure", {
            //         location: this.state.location
            //       })
            //     }
            //   />
            // })

          }
        </MapView>
        <View
          style={{
            alignItems: "center",
            height: "20%",
            width: "100%",
            justifyContent: "center"
          }}
        >
          <Text style={styles.fontStyle}>
            핀을 터치해서 자전거를 선택하세요!
          </Text>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center"
  },
  fontStyle: {
    fontSize: 20,
    fontFamily: "sunflower",
    color: "white"
  }
});

export default MapScreen;

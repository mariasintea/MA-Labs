import 'package:discover_destination/model/destination.dart';
import 'package:flutter/material.dart';

class ViewOneScreen extends StatefulWidget {
  Destination destination;

  ViewOneScreen(this.destination, {Key? key}) : super(key: key);

  @override
  _ViewOneState createState() => _ViewOneState(destination);
}

class _ViewOneState extends State<ViewOneScreen> {
  Destination destination;

  _ViewOneState(this.destination);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.white,
        body: Column(children: <Widget>[
          Image.network(destination.image),
          Card(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Padding(
                    padding: const EdgeInsets.all(20.0),
                    child: Row(children: [
                      Expanded(
                          flex: 75,
                          child: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: <Widget>[
                                Text(destination.city,
                                    style: TextStyle(fontSize: 40.0)),
                                SizedBox(
                                  height: 10.0,
                                ),
                                Text(destination.country,
                                    style: TextStyle(fontSize: 25.0))
                              ])),
                      Expanded(
                          flex: 25,
                          child: Text(destination.tourism,
                              style: TextStyle(fontSize: 20.0)))
                    ])),
                Padding(
                    padding: const EdgeInsets.all(20.0),
                    child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                      Text(destination.description,
                          textAlign: TextAlign.left,
                          style: TextStyle(fontSize: 20.0)),
                      SizedBox(
                        height: 20.0,
                      ),
                      Text("List of toursit attractions:",
                          style: TextStyle(fontSize: 25.0)),
                          SizedBox(
                            height: 10.0,
                          ),
                      Column(
                          children: destination.touristAttractions
                              .map((attraction) => Row(
                                    children: <Widget>[
                                      Icon(Icons.location_on),
                                      Text(attraction,
                                          style: TextStyle(fontSize: 20.0))
                                    ],
                                  ))
                              .toList())
                    ]))
              ],
            ),
          )
        ]));
  }
}

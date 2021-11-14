import 'package:discover_destination/model/destination.dart';
import 'package:discover_destination/view/edit_screen.dart';
import 'package:discover_destination/viewmodel/destinations_view_model.dart';
import 'package:flutter/material.dart';

class ModifyScreen extends StatefulWidget {
  DestinationsViewModel destinationsViewModel;
  String user;

  ModifyScreen(this.destinationsViewModel, this.user, {Key? key})
      : super(key: key);

  @override
  _ModifyState createState() => _ModifyState(destinationsViewModel, user);
}

class _ModifyState extends State<ModifyScreen> {
  DestinationsViewModel destinationsViewModel;
  String user;

  _ModifyState(this.destinationsViewModel, this.user);

  @override
  Widget build(BuildContext context) {
    List<Destination> destinations = destinationsViewModel.getAll(user);
    return Scaffold(
        backgroundColor: Colors.white,
        body: SingleChildScrollView(
            child: Column(children: <Widget>[
          Padding(
            padding: const EdgeInsets.only(top: 60.0),
            child: Center(
              child: Container(
                  height: 70,
                  child: Text("Modify Destination",
                      textAlign: TextAlign.left,
                      style: TextStyle(fontSize: 40.0))),
            ),
          ),
          Padding(
              padding: EdgeInsets.symmetric(horizontal: 15),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: destinations
                    .map((destination) => Card(
                        color: Colors.white10,
                        child: Padding(
                            padding: const EdgeInsets.all(20.0),
                            child: Row(
                              children: <Widget>[
                                Expanded(
                                    flex: 9,
                                    child: Column(
                                        crossAxisAlignment:
                                            CrossAxisAlignment.start,
                                        children: <Widget>[
                                          Text(destination.city,
                                              style: TextStyle(fontSize: 25.0)),
                                          SizedBox(
                                            height: 10.0,
                                          ),
                                          Text(destination.country,
                                              style: TextStyle(fontSize: 15.0))
                                        ])),
                                Expanded(
                                    flex: 1,
                                    child: IconButton(
                                        onPressed: () => {
                                              Navigator.push(
                                                  context,
                                                  MaterialPageRoute(
                                                      builder: (_) => EditScreen(
                                                          destinationsViewModel,
                                                          destination)))
                                            },
                                        icon: Icon(Icons.edit)))
                              ],
                            ))))
                    .toList(),
              ))
        ])));
  }
}

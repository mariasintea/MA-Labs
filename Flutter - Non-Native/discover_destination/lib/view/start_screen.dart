import 'dart:io';

import 'package:discover_destination/view/add_screen.dart';
import 'package:discover_destination/view/delete_screen.dart';
import 'package:discover_destination/view/modify_screen.dart';
import 'package:discover_destination/view/view_all_screen.dart';
import 'package:discover_destination/viewmodel/destinations_view_model.dart';
import 'package:flutter/material.dart';

class StartScreen extends StatefulWidget {
  DestinationsViewModel destinationsViewModel;
  String user;

  StartScreen(this.destinationsViewModel, this.user, {Key? key}) : super(key: key);

  @override
  _StartState createState() => _StartState(destinationsViewModel, user);
}

class _StartState extends State<StartScreen> {
  DestinationsViewModel destinationsViewModel;
  String user;

  _StartState(this.destinationsViewModel, this.user);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.white,
        body: SingleChildScrollView(
            child: Column(children: <Widget>[
          Padding(
            padding: const EdgeInsets.only(top: 60.0),
            child: Center(
              child: Container(
                  width: 300,
                  height: 300,
                  child: Image.asset("images/logo5.jpg")
              ),
            ),
          ),
          Container(
              decoration: BoxDecoration(
                  color: Colors.white, borderRadius: BorderRadius.circular(20)),
              child: Column(children: <Widget>[
                SizedBox(height: 10),
                OutlinedButton(
                  onPressed: () {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (_) => AddScreen(destinationsViewModel, user)));
                  },
                  child: Text(
                    'Add Destination',
                    style: TextStyle(color: Colors.black, fontSize: 25),
                  ),
                style: OutlinedButton.styleFrom( minimumSize: Size(300, 50)),),
                SizedBox(height: 10),
                OutlinedButton(
                  onPressed: () {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (_) => DeleteScreen(destinationsViewModel, user)));
                  },
                  child: Text(
                    'Delete Destination',
                    style: TextStyle(color: Colors.black, fontSize: 25),),
                  style: OutlinedButton.styleFrom( minimumSize: Size(300, 50)),),
                SizedBox(height: 10),
                OutlinedButton(
                  onPressed: () {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (_) => ModifyScreen(destinationsViewModel, user)));
                  },
                  child: Text(
                    'Modify Destination',
                    style: TextStyle(color: Colors.black, fontSize: 25),
                  ),
                  style: OutlinedButton.styleFrom( minimumSize: Size(300, 50)),),
                SizedBox(height: 10),
                OutlinedButton(
                  onPressed: () {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (_) => ViewAllScreen(destinationsViewModel, user)));
                  },
                  child: Text(
                    'View All Destinations',
                    style: TextStyle(color: Colors.black, fontSize: 25),
                  ),
                 style: OutlinedButton.styleFrom( minimumSize: Size(300, 50)),)
              ]
          )
          )
        ])));
  }
}

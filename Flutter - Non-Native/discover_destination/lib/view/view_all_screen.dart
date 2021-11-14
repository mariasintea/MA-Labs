import 'package:discover_destination/model/destination.dart';
import 'package:discover_destination/view/view_one_screen.dart';
import 'package:discover_destination/viewmodel/destinations_view_model.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class ViewAllScreen extends StatefulWidget {
  DestinationsViewModel destinationsViewModel;
  String user;

  ViewAllScreen(this.destinationsViewModel, this.user, {Key? key})
      : super(key: key);

  @override
  _ViewAllState createState() => _ViewAllState(destinationsViewModel, user);
}

class _ViewAllState extends State<ViewAllScreen> {
  DestinationsViewModel destinationsViewModel;
  List<Destination> _destinations = List.empty();
  String user;
  final countryTextFieldController = TextEditingController();

  _ViewAllState(this.destinationsViewModel, this.user);

  @override
  initState() {
    _destinations = destinationsViewModel.getAll(user);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.white,
        body: SingleChildScrollView(
            child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
              Padding(
                padding: const EdgeInsets.fromLTRB(15.0, 60.0, 0.0, 0.0),
                child: Container(
                    height: 70,
                    child: Text("Destinations",
                        textAlign: TextAlign.left,
                        style: TextStyle(fontSize: 50.0))),
              ),
                  Padding(
                    padding: const EdgeInsets.all(15.0),
                    child: Row(
                      children: [
                        Expanded(
                            flex: 9,
                            child: TextField(
                                decoration: InputDecoration(
                                    border: OutlineInputBorder(),
                                    contentPadding: EdgeInsets.only(left: 15.0, top: 15.0),
                                    hintText: 'Search by country...'),
                            controller: countryTextFieldController,)
                        ),
                        Expanded(
                            flex: 1,
                            child: IconButton(
                                onPressed: () => {
                                  setState( () => {
                                    _destinations = destinationsViewModel.filter(countryTextFieldController.text, user)
                                  })
                                 },
                                icon: Icon(Icons.search)))
                      ],
                    )
                  ),
              Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: _destinations
                      .map((destination) => GestureDetector(
                          onTap: () => {
                                Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (_) =>
                                            ViewOneScreen(destination)))
                              },
                          child: Card(
                              margin: EdgeInsets.all(20.0),
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: <Widget>[
                                  Image.network(destination.image),
                                  Padding(
                                      padding: const EdgeInsets.all(15.0),
                                      child: Column(
                                          crossAxisAlignment:
                                              CrossAxisAlignment.start,
                                          children: <Widget>[
                                            Text(destination.city,
                                                style:
                                                    TextStyle(fontSize: 30.0)),
                                            Text(destination.country,
                                                style:
                                                    TextStyle(fontSize: 20.0))
                                          ]))
                                ],
                              ))))
                      .toList())
            ])));
  }
}

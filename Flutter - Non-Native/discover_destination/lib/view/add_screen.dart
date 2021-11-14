import 'package:discover_destination/model/destination.dart';
import 'package:discover_destination/view/start_screen.dart';
import 'package:discover_destination/viewmodel/destinations_view_model.dart';
import 'package:flutter/material.dart';

class AddScreen extends StatefulWidget {
  DestinationsViewModel destinationsViewModel;
  String user;

  AddScreen(this.destinationsViewModel, this.user, {Key? key})
      : super(key: key);

  @override
  _AddState createState() => _AddState(destinationsViewModel, user);
}

class _AddState extends State<AddScreen> {
  DestinationsViewModel destinationsViewModel;
  String user;
  final cityTextFieldController = TextEditingController();
  final countryTextFieldController = TextEditingController();
  final tourismTextFieldController = TextEditingController();
  final imageTextFieldController = TextEditingController();
  final descriptionTextFieldController = TextEditingController();
  final attractionTextFieldController = TextEditingController();
  final attractionsTextFieldController = TextEditingController();

  _AddState(this.destinationsViewModel, this.user);

  bool validate(Destination destination) {
    return destination.city.length > 0 &&
        destination.country.length > 0 &&
        destination.description.length > 0 &&
        destination.image.length > 0 &&
        destination.tourism.length > 0 &&
        !destination.touristAttractions.isEmpty;
  }

  @override
  Widget build(BuildContext context) {
    Destination destination;
    List<String> attractions;

    return Scaffold(
        backgroundColor: Colors.white,
        body: SingleChildScrollView(
            child: Column(children: <Widget>[
          Padding(
            padding: const EdgeInsets.only(top: 60.0),
            child: Center(
              child: Container(
                  height: 70,
                  child: Text("Add Destination",
                      textAlign: TextAlign.left,
                      style: TextStyle(fontSize: 45.0))),
            ),
          ),
          Padding(
              padding: EdgeInsets.symmetric(horizontal: 15),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  TextField(
                    decoration: InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: 'City',
                    ),
                    controller: cityTextFieldController,
                  ),
                  SizedBox(
                    height: 10.0,
                  ),
                  TextField(
                    decoration: InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: 'Country',
                    ),
                    controller: countryTextFieldController,
                  ),
                  SizedBox(
                    height: 10.0,
                  ),
                  TextField(
                    decoration: InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: 'Type of Tourism',
                    ),
                    controller: tourismTextFieldController,
                  ),
                  SizedBox(
                    height: 10.0,
                  ),
                  TextField(
                    decoration: InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: 'Image URL',
                    ),
                    controller: imageTextFieldController,
                  ),
                  SizedBox(
                    height: 10.0,
                  ),
                  TextField(
                    decoration: InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: 'Description',
                    ),
                    controller: descriptionTextFieldController,
                  ),
                  SizedBox(
                    height: 10.0,
                  ),
                  Row(
                    children: <Widget>[
                      Expanded(
                        flex: 85,
                        child: TextField(
                          decoration: InputDecoration(
                            border: OutlineInputBorder(),
                            labelText: 'Tourist Attraction',
                          ),
                          controller: attractionTextFieldController,
                        ),
                      ),
                      Expanded(
                        flex: 15,
                        child: FlatButton(
                            onPressed: () => {
                                  attractionsTextFieldController.text +=
                                      attractionTextFieldController.text + ", ",
                                  attractionTextFieldController.text = ""
                                },
                            child: Icon(Icons.add),
                            ),
                      )
                    ],
                  ),
                  SizedBox(
                    height: 10.0,
                  ),
                  Text("List of Tourist Attractions:",
                      textAlign: TextAlign.left,
                      style: TextStyle(fontSize: 20.0)),
                  TextFormField(
                    readOnly: true,
                    decoration: InputDecoration(border: OutlineInputBorder()),
                    controller: attractionsTextFieldController,
                  ),
                  SizedBox(
                    height: 10.0,
                  ),
                  Center(
                    child:
                  OutlinedButton(
                    onPressed: () => {
                      attractions =
                          attractionsTextFieldController.text.split(", "),
                      destination = Destination(
                          cityTextFieldController.text,
                          countryTextFieldController.text,
                          tourismTextFieldController.text,
                          descriptionTextFieldController.text,
                          imageTextFieldController.text,
                          attractions.sublist(0, attractions.length - 1),
                          user),
                      if (validate(destination))
                        {
                          destinationsViewModel.add(destination),
                          Navigator.push(
                              context,
                              MaterialPageRoute(
                                  builder: (_) =>
                                      StartScreen(destinationsViewModel, user)))
                        }
                      else
                        {
                          showDialog(
                              context: context,
                              builder: (ctx) => AlertDialog(
                                    title: Text("Invalid data"),
                                    content:
                                        Text("All fields must be completed!"),
                                    actions: <Widget>[
                                      FlatButton(
                                        onPressed: () {
                                          Navigator.of(ctx).pop();
                                        },
                                        child: Text("OK"),
                                      ),
                                    ],
                                  ))
                        }
                    },
                    child: Text(
                      'ADD DESTINATION',
                      style: TextStyle(color: Colors.black, fontSize: 25),
                    ),
                  ))
                ],
              ))
        ])));
  }
}

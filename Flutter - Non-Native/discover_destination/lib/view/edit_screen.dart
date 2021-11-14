import 'package:discover_destination/model/destination.dart';
import 'package:discover_destination/view/start_screen.dart';
import 'package:discover_destination/viewmodel/destinations_view_model.dart';
import 'package:flutter/material.dart';

class EditScreen extends StatefulWidget {
  DestinationsViewModel destinationsViewModel;
  Destination destination;

  EditScreen(this.destinationsViewModel, this.destination, {Key? key})
      : super(key: key);

  @override
  _EditState createState() => _EditState(destinationsViewModel, destination);
}

class _EditState extends State<EditScreen> {
  DestinationsViewModel destinationsViewModel;
  Destination destination;
  final cityTextFieldController = TextEditingController();
  final countryTextFieldController = TextEditingController();
  final tourismTextFieldController = TextEditingController();
  final imageTextFieldController = TextEditingController();
  final descriptionTextFieldController = TextEditingController();
  final attractionTextFieldController = TextEditingController();
  final attractionsTextFieldController = TextEditingController();

  _EditState(this.destinationsViewModel, this.destination);

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
    Destination newDestination;
    cityTextFieldController.text = destination.city;
    countryTextFieldController.text = destination.country;
    descriptionTextFieldController.text = destination.description;
    imageTextFieldController.text = destination.image;
    tourismTextFieldController.text = destination.tourism;
    attractionsTextFieldController.text = destination.touristAttractions
        .reduce((string, el) => string + ", " + el);
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
                  child: Text("Edit Destination",
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
                    Text("Tourist Attractions"),
                    TextField(
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
                        newDestination = Destination(
                            cityTextFieldController.text,
                            countryTextFieldController.text,
                            tourismTextFieldController.text,
                            descriptionTextFieldController.text,
                            imageTextFieldController.text,
                            attractions,
                            destination.user),
                        if (validate(newDestination))
                          {
                            destinationsViewModel.modify(newDestination),
                            Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (_) => StartScreen(
                                        destinationsViewModel,
                                        destination.user)))
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
                        'SAVE DESTINATION',
                        style: TextStyle(color: Colors.black, fontSize: 25),
                      ),
                    ))
                  ]))
        ])));
  }
}

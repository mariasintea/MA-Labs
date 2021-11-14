import 'package:discover_destination/model/user.dart';
import 'package:discover_destination/viewmodel/destinations_view_model.dart';
import 'package:discover_destination/viewmodel/users_view_model.dart';
import 'package:discover_destination/view/start_screen.dart';
import 'package:flutter/material.dart';

class LoginScreen extends StatefulWidget {
  UsersViewModel usersViewModel;
  DestinationsViewModel destinationsViewModel;

  LoginScreen(this.destinationsViewModel, this.usersViewModel, {Key? key}) : super(key: key);

  @override
  _LoginState createState() => _LoginState(destinationsViewModel, usersViewModel);
}

class _LoginState extends State<LoginScreen> {
  UsersViewModel usersViewModel;
  DestinationsViewModel destinationsViewModel;
  final usernameTextFieldController = TextEditingController();
  final passwordTextFieldController = TextEditingController();

  _LoginState(this.destinationsViewModel, this.usersViewModel);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: SingleChildScrollView(
        child: Column(
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.only(top: 175.0),
              child: Center(
                child: Container(
                    height: 150,
                    child: Text("Log In", style: TextStyle(fontSize: 60.0))
                ),
              ),
            ),
            Padding(
              padding: EdgeInsets.symmetric(horizontal: 15),
              child: TextField(
                decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Username',),
                controller: usernameTextFieldController,
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(
                  left: 15.0, right: 15.0, top: 15, bottom: 15),
              //padding: EdgeInsets.symmetric(horizontal: 15),
              child: TextField(
                obscureText: true,
                decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Password'),
                controller: passwordTextFieldController,
              ),
            ),
            SizedBox(
              height: 15.0,
            ),
            Container(
              height: 50,
              width: 250,
              decoration: BoxDecoration(
                  color: Colors.white, borderRadius: BorderRadius.circular(20)),
              child: OutlinedButton(
                onPressed: () => {
                  if (usersViewModel.checkUser(User(usernameTextFieldController.text, passwordTextFieldController.text)))
                    Navigator.push(
                        context, MaterialPageRoute(builder: (_) => StartScreen(destinationsViewModel, usernameTextFieldController.text)))
                  else {
                    showDialog(
                        context: context,
                        builder: (ctx) => AlertDialog(
                      title: Text("Invalid data"),
                      content: Text("Username and password don't match!"),
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
                  'LOG IN',
                  style: TextStyle(color: Colors.black, fontSize: 25),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.deepPurple,
      ),
      home: const MyHomePage(title: 'Hola como estas'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage>
    with SingleTickerProviderStateMixin {
  String _texto = 'Texto inicial';
  bool _mostrarImagen = false;
  late AnimationController _controller;

  void _cambiarTexto() {
    setState(() {
      if (_texto == 'Texto inicial') {
        _texto = 'Texto cambiado';
      } else {
        _texto = 'Texto inicial';
      }
    });
  }

  void _toggleImagen() {
    setState(() {
      _mostrarImagen = !_mostrarImagen;
    });
    _controller.forward(from: 0); // reinicia y comienza la animación
  }

  @override
  void initState() {
    super.initState();
    _controller = AnimationController(
      duration: const Duration(milliseconds: 300),
      vsync: this,
    );
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.deepPurple,
        title: Text(
          'Mi Aplicación Flutter',
          style: TextStyle(fontWeight: FontWeight.bold),
        ),
      ),
      body: Container(
        color: Colors.purple[50], // Cambiado color de fondo
        child: Center(
          child: SingleChildScrollView(
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Text(
                    _texto,
                    style: TextStyle(fontSize: 20.0, color: Colors.blueGrey),
                  ),
                  const SizedBox(height: 10),
                  if (_mostrarImagen)
                    Container(
                      width: 200,
                      height: 200,
                      decoration: BoxDecoration(
                        boxShadow: [
                          BoxShadow(
                            blurRadius: 10,
                            color: Colors.black26,
                          ),
                        ],
                      ),
                      child: Image.asset(
                        'assets/tarea.png',
                        fit: BoxFit.cover,
                      ),
                    ),
                  const SizedBox(height: 10),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: <Widget>[
                      ElevatedButton(
                        onPressed: _cambiarTexto,
                        child: Text('Presioname'),
                        style: ElevatedButton.styleFrom(
                          primary: Colors.deepPurple,
                        ),
                      ),
                      Icon(Icons.star, size: 50),
                      RotationTransition(
                        turns: _controller,
                        child: ElevatedButton(
                          onPressed: _toggleImagen,
                          child: Text('Mostrar/Ocultar Imagen'),
                          style: ElevatedButton.styleFrom(
                            primary: Colors.deepPurple,
                          ),
                        ),
                      ),
                    ],
                  ),
                  Container(
                    padding: EdgeInsets.all(10.0),
                    margin: EdgeInsets.all(10.0),
                    color: Colors.blue,
                    child: Text(
                      'Contenedor Personalizado',
                      style: TextStyle(fontSize: 20.0, color: Colors.white),
                    ),
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}

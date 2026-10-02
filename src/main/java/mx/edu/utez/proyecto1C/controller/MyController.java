package mx.edu.utez.proyecto1C.controller;



import jakarta.validation.Valid;
import mx.edu.utez.proyecto1C.controller.dto.*;
import mx.edu.utez.proyecto1C.service.CotizadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mx.edu.utez.proyecto1C.service.MyService;
import mx.edu.utez.proyecto1C.service.VehiculoService;
import mx.edu.utez.proyecto1C.service.HospedajeService;

@RestController
@CrossOrigin({"*"}) // todos los origenes
@RequestMapping("/my-services")
public class MyController {

    private final MyService service;
    private final CotizadorService cotizadorService;
    private final VehiculoService vehiculoService;
    private final HospedajeService hospedajeService;

    //inyeccion de dependencias
    public MyController(
            MyService service,
            CotizadorService cotizadorService,
            VehiculoService vehiculoService,
            HospedajeService hospedajeService) {

        this.service = service;
        this.cotizadorService = cotizadorService;
        this.vehiculoService = vehiculoService;
        this.hospedajeService = hospedajeService;
    }

    @GetMapping
    public String miPrimerServicio() {
        return "Hello world";
    }

    @GetMapping("/sevicio2")
    public String servicio2() {
        return "segundo servicio";
    }

    @PostMapping
    public String servicio3() {
        return "este es el servicio 3";
    }

    //
    @GetMapping("/path/{id}")
    public String pathvariable(@PathVariable String id) {
        return "el path variable es: " + id;

    }

    @PostMapping("/body")
    public ResponseEntity<RequestBodyDTO> body(@RequestBody @Valid RequestBodyDTO payload) {
        System.out.println(payload.getEdad());
        System.out.println(payload.getNombre());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(payload);
    }

    //tarea
    //FizzBuzz

    @GetMapping("/fizzbuzz/{n}")
    public String fizzbuzz(@PathVariable int n) {
        for (int i = 1; i <= n; i++) {

            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");

            } else if (i % 5 == 0) {
                System.out.println("Buzz");

            } else {
                System.out.println(i);
            }
        }
        return "Lucero Bahena Santana";
    }


    //Fibonacci

    @GetMapping("/fibonacci/{n}")
    public String fibonacci(@PathVariable int n) {
        int primero = 0;
        int segundo = 1;

        for (int i = 0; i <= n; i++) {
            System.out.println(primero);

            int siguiemte = primero + segundo;
            primero = segundo;
            segundo = siguiemte;
        }
        return "Lucero Bahena Santana";
    }

    @PostMapping("/calculadora")
    public double calculadora(@RequestBody @Valid RequestCalculadoraDTO payload) {
        return service.calculadora(payload);

    }


    //tarea- ejercicio1
    @PostMapping("/cotizador")
    public ResponseEntity<Double> cotizar(
            @RequestBody @Valid CotizadorDTO payload) {

        double resultado = cotizadorService.cotizar(payload);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultado);
    }

    // tarea-ejercicio2
    @PostMapping("/vehiculo")
    public ResponseEntity<Double> vehiculo(
            @RequestBody @Valid RequestVehiculoDTO payload) {

        double resultado = VehiculoService.cotizar(payload);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultado);
    }

    // tarea-ejercicio3
    @PostMapping("/hospedaje")
    public ResponseEntity<Double> hospedaje(
            @RequestBody @Valid RequestHospedajeDTO payload) {

        double resultado = HospedajeService.cotizar(payload);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultado);
    }
}

import serial
import time

# Configuración del puerto serie
ser = serial.Serial('/dev/ttyUSB0', baudrate=9600, timeout=1)  
time.sleep(2)  # Tiempo para establecer la conexión

def activar_puerto(numero_puerto):
    comando = f"ON {numero_puerto}\r\n"
    ser.write(comando.encode())
    print(f"Activando puerto {numero_puerto}")

def desactivar_puerto(numero_puerto):
    comando = f"OFF {numero_puerto}\r\n"
    ser.write(comando.encode())
    print(f"Desactivando puerto {numero_puerto}")

# Activar y desactivar cada puerto durante 10 segundos
for puerto in range(1, 5):
    activar_puerto(puerto)
    time.sleep(1)
    desactivar_puerto(puerto)

# Cerrar la conexión
ser.close()

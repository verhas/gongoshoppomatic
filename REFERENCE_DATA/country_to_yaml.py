from yaml import load, dump
try:
    from yaml import CLoader as Loader, CDumper as Dumper
except ImportError:
    from yaml import Loader, Dumper

with open("countries.txt","r") as f:


# ...

data = load(stream, Loader=Loader)

# ...

output = dump(data, Dumper=Dumper)
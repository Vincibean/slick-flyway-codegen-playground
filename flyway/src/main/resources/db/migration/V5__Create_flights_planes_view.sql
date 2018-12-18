create view FLIGHTS_PLANES as
select f.year, f.month, f.dayOfMonth, f.scheduledDepTime, f.actualElapsedTime, f.scheduledElapsedTime, p.tailNum, p.model, p.manufacturer
from planes p join flights f
on p.tailNum = f.tailNum;

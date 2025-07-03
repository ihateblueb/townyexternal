# Contributing

## Tips

"Mini" data classes are for nested objects that otherwise would cause stack overflows. (eg. Resident has `town` attribute, so when showing a town's mayor, `MiniResident` is used which omits `town`).
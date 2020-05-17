# Homeworks for "Java Programming" course

## Task 1. Image processing.

Your task is to implement methods: `impl.image.ImageConverterImpl.convertToColor`, `impl.image.ImageConverterImpl.convertToRgb` and `impl.image.ConvolutionProviderImpl.apply`.
You should write an implementation where TODO is written.

Method `impl.image.ImageConverterImpl.convertToColor` should transform `int[][]` to `Color[][]`, method `impl.image.ImageConverterImpl.convertToRgb` should transform `Color[][]` to `int[][]`.
Where `int` contains information about color this way:
  * 0-7 bits contain info about `blue` color, 
  * 8-15 bits contain info about `green` color, 
  * 16-23 bits contain info about `red` color,
  * 24-31 bits contains info about a transparency (`alpha` layer).

`impl.image.ConvolutionProviderImpl.apply` should apply [convolution](https://en.wikipedia.org/wiki/Kernel_(image_processing)) operation
on given image, using given kernel (guaranteed that kernel dimensions are > 0).
  *  You should apply convolution operation for each color separately.
  *  Image dimensions shouldn't be changed.
  *  If during convolution operation, the core element goes beyond the picture, it should be considered that `red, green, blue = 0`.
  *  `alpha` should be always `255`.
  *  After each multiplication of the color value by the kernel element, result should be rounded to `0`.

If you want to experiment with other cores you can use class `ImageUtil` it contains methods `writeOutputImage`, `readOriginImage` 
which allows reading images from `resources/image/origin` folder and writing images to `resources/image/output` folder.
Using them you can watch how the convolution operation affects images. 
  *  All pictures must be in the `png` format.
  *  When you specify the name of the image, the format should also be indicated (e.g. `pic1.png`).
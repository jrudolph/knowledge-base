# Machine Learning

## Face Recognition

### face_recognition
 
[Github](https://github.com/ageitgey/face_recognition)
 
Seems to be very popular (16000+ stars). Simple to use, just create a single folder with photos which show a single person.
Then also point it to another folder with photos and it will detect and recognize persons in there.

Command line usage has the problem that only giving a single reference photo might be worse than giving a corpus of reference images.
(But that should be easy build on top of face_recognition/dlib)  

Turns out it is only a super simple ([200 lines](https://github.com/ageitgey/face_recognition/blob/master/face_recognition/api.py))
python wrapper over [dlib](http://dlib.net/)'s "state-of-the-art face recognition" python API. dlib only has C++ and python
APIs.

 
### dlib

[Homepage](http://dlib.net/) | [Github](https://github.com/davisking/dlib)

C++ and python APIs for all kinds of mathematical and ML tasks.

It seems no effort is made of providing a Java binding [any time soon](https://github.com/davisking/dlib/issues/681).
Instead, the developers point to [SWIG](http://www.swig.org/) for creating bindings semi-automatically (which means
writing simple enough C++ code to be auto-converted into Java bindings).
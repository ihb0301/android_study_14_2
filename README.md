# android_study_14_2

아롬 안드로이드 14기 스터디 과제 2번


레포지토리에 익숙해지고 기존에 사용하던 Recyclerview 대신 ListAdapter + DiffUtil 을 이용해 효율적인 코드를 작성해봐요

api와 통신하는 mvvm 코드 작성해보기

<img src = "https://github.com/user-attachments/assets/2a6e3d17-8161-42dd-b14c-25f87f54017f" width = "40%" height = "40%">  
<img src = "https://github.com/user-attachments/assets/60efbe08-e6ff-49ec-a1cb-f499daf338c7" width = "40%" height = "40%">  
<br><br>

앱이 오픈될때 메인액티비티에서 api를 호출하고 그 결과를 보여줍니다. 그리고 추가하기 버튼을 누르고 제목, 가격, 시간만 텍스트로 입력 받고 저장할 수 있게 해주세요. 저장할때 add api 호출하시면 됩니다

저장이 되면 창을 닫고 메인 액티비티로 돌아와서 다시 api를 호출해주세요.

필수

- ui는 정해진 틀에서 벗어나지 않는다면 자유롭게 수정하셔도 됩니다.
- viewbinding 사용 databinding 사용안함
- mvvm + stateflow를 이용합니다
- 레포지토리 패턴을 꼭 이용합니다.
- 기존에 사용하던 리사이클러뷰 대신 ListAdapter와 DiffUtil을 이용해 notifyDataSetChange 없이 효율적인 갱신 로직을 처리해봅시다. [https://rkdrkd-history.tistory.com/149](https://rkdrkd-history.tistory.com/149) [(이론적으로자세히알고싶다면)](https://dev.gmarket.com/79)
- 디자인같은건 마음대로 구성하셔도 상관 없습니다.
- usecase를 이용해봅시다.  (어렵다면 여기까진 안하셔도 괜찮아요. 그래도 특별한 사정이 없으면 하시는걸 추천드리긴 합니다) [https://velog.io/@cchloe2311/안드로이드-UseCase를-왜-쓰나요](https://velog.io/@cchloe2311/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-UseCase%EB%A5%BC-%EC%99%9C-%EC%93%B0%EB%82%98%EC%9A%94) [https://velog.io/@sonny8569/F-Lab-모각코-챌린지-Android-usecase-적용기](https://velog.io/@sonny8569/F-Lab-%EB%AA%A8%EA%B0%81%EC%BD%94-%EC%B1%8C%EB%A6%B0%EC%A7%80-Android-usecase-%EC%A0%81%EC%9A%A9%EA%B8%B0) (di필요)

선택

- hilt를 이용해서 뷰모델, 레포지토리 등 의존성이 필요한곳에 주입해 봅시다  (다음과제엔 필수) [https://velog.io/@heymoko/Android-DI는-뭐고-Hilt는-뭔데요](https://velog.io/@heymoko/Android-DI%EB%8A%94-%EB%AD%90%EA%B3%A0-Hilt%EB%8A%94-%EB%AD%94%EB%8D%B0%EC%9A%94) [https://it-of-fortune.tistory.com/26](https://it-of-fortune.tistory.com/26)
- api오류가 발생했을떄 오류메세지를 띄우는 등 핸들링을 해봅시다. (result값 이용)
- 클린아키텍쳐에 대해서 고민해보고, 과제에서 제시하는 부분 말고도 다른거 뭐든지 적용해봐요

---

**과제 진행 방법**

해당 레포지토리를 본인 계정으로 포크해가세요.

과제가 완료 되면 해당 레포지토리에 pull requests를 열어주세요.

pull requests에 해당 코드에 대한 간단한 설명을 적어주세요.

코드에 대한 의문점이 있다면 같이 남겨주세요

pull requests에 앱의 스크린샷도 같이 남겨주세요.

---

https://app.swaggerhub.com/apis-docs/SEONGHWI/alom/1.0.0

먼저 필요한 api 주소는 스웨거에 정리해뒀습니다.

스웨거 열면 오른쪽 위에 Authorize 자물쇠 버튼 누르면 key 입력하는곳 나옵니다. 거기에 키 붙여넣기 하시고 인증하시면 됩니다. (브라우저 열때마다 해야함.) 

그리고 사용해보고 싶은 api에 try it out 누른 후, execute하시면 됩니당

api 키는 카톡으로 공유합니다. 꼭 깃에 올릴때 local.properties 안에 키 숨겨서 올려주세요 (아니면 제 돈 나가요 ㅠㅠ)

레트로핏 호출부 or 레트로핏 빌더에 **Interceptor 추가 (추천)에서 (“apikey”, “keykeykeyekykey”)이런식으로 추가하시면 됩니다**

각자 개인 api 주소를 만들어 드렸으니까 사용하시면 됩니다.

만약 api 연결하다가 뭔가 에러가난다 하시면 저에게 연락주세요

api 호출할때 꼭 apikey를 붙여야합니다. 

개발하다가 데이터 너무 많이 쌓여서 삭제하고 싶으면 연락주세요

---

힌트 

서버에서는 스네이크 케이스를 사용하기 때문에 json에 _가 붙어있는 경우가 많습니다.

그럴때는 SerializedName을 이용해보세요.

안드로이드에서 변수명은 무조건 카멜케이스 사용해야합니다.

---

### 과제 진행 방법

해당 레포지토리를 본인 계정으로 포크해가세요.

과제가 완료 되면 해당 레포지토리에 pull requests를 열어주세요. 

pull requests에 해당 코드에 대한 간단한 설명을 적어주세요.

코드에 대한 의문점이 있다면 같이 남겨주세요

pull requests에 앱의 스크린샷도 같이 남겨주세요.

---

### 커밋 룰

| 태그 이름 | 설명 |
| --- | --- |
| feat: | 새로운 기능을 추가할 경우 |
| fix: | 버그를 고친경우 |
| design: | xml 등 사용자 UI 디자인 변경 |
| style: | 코드 포맷 변경, 린트 수정, 코드 수정이 없는 경우 |
| comment: | 필요한 주석 추가 및 변경 |
| docs: | 문서를 수정한 경우 |
| test: | 테스트 추가, 테스트 리팩토링(프로덕션 코드 변경 X) |
| chore: | 빌드 태스트 업데이트, 패키지 매니저를 설정하는 경우(프로덕션 코드 변경 X) |
| rename: | 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우 |
| remove: | 파일을 삭제하는 작업만 수행한 경우 |
| setting: | Gradle, Manifest 등 파일에 세팅 추가 |

커밋할때는 태그를 꼭 붙여주세요.

예시)

```kotlin
- feat(MainActivity, MainViewModel): EditText를 SharedPreference에 저장하는 로직 추가

- comment(MainViewModel): SharedPreference 로직 주석 추가
```

커밋은 최대한 자주 해주세요.

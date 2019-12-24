<#import "parts/commonForForm.ftl" as c>

<@c.page>
    <main>
        <h1 style="text-align: center">Добавление скидки</h1>
        <div class="container border">
            <form method="post">
                <div class="form-group">
                    <label>Скидка</label>
                    <div class="input-group">
                        <input class="form-control" type="number" name="Скидка" placeholder="Введите скидку" value="0"
                               aria-describedby="addon1">
                        <div class="input-group-append">
                            <span class="input-group-text" id="addon1">%</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-3">
                        <label>На типы</label>
                        <div class="form-check pr-5">
                            <input class="form-check-input" type="checkbox" name="Тип"
                                   id="exampleRadios1" value="комикс">
                            <label class="form-check-label" for="комикс">
                                Комикс
                            </label>
                        </div>
                        <div class="form-check pr-5">
                            <input class="form-check-input" type="checkbox" name="Тип"
                                   id="exampleRadios1" value="манга">
                            <label class="form-check-label" for="exampleRadioss1">
                                Манга
                            </label>
                        </div>
                        <div class="form-check pr-5">
                            <input class="form-check-input" type="checkbox" name="Тип"
                                   id="exampleRadios1" value="артбук">
                            <label class="form-check-label" for="exampleRadioss1">
                                Артбук
                            </label>
                        </div>
                    </div>
                    <div class="form-group col-3">
                        <label>На возраст</label>
                        <div class="form-check pr-5">
                            <input class="form-check-input" type="checkbox" name="Категория"
                                   id="exampleRadios1" value="Для детей">
                            <label class="form-check-label" for="exampleRadios1">
                                Для детей
                            </label>
                        </div>
                        <div class="form-check pr-5">
                            <input class="form-check-input" type="checkbox" name="Категория"
                                   id="exampleRadios1" value="Для подростков">
                            <label class="form-check-label" for="Для подростков">
                                Для подростков
                            </label>
                        </div>
                        <div class="form-check pr-5">
                            <input class="form-check-input" type="checkbox" name="Категория"
                                   id="exampleRadios1" value="Для взрослых">
                            <label class="form-check-label" for="Для взрослых">
                                Для взрослых
                            </label>
                        </div>
                    </div>
                    <div class="form-group col-3">
                        <label>На страну</label>
                        <div class="form-check pr-5">
                            <input class="form-check-input" type="checkbox" name="Страна"
                                   id="exampleRadios1" value="Россия">
                            <label class="form-check-label" for="Россия">
                                Россия
                            </label>
                        </div>
                        <div class="form-check pr-5">
                            <input class="form-check-input" type="checkbox" name="Страна"
                                   id="exampleRadios1" value="США">
                            <label class="form-check-label" for="США">
                                США
                            </label>
                        </div>
                        <div class="form-check pr-5">
                            <input class="form-check-input" type="checkbox" name="Страна"
                                   id="exampleRadios1" value="Япония">
                            <label class="form-check-label" for="Япония">
                                Япония
                            </label>
                        </div>
                    </div>
                    <div class="form-group col-3">
                        <label>На издательство</label>
                        <div class="form-check pr-5">
                            <input class="form-check-input" type="checkbox" name="Издательство"
                                   id="exampleRadios1" value="Marvel">
                            <label class="form-check-label" for="Marvel">
                                Marvel
                            </label>
                        </div>
                        <div class="form-check pr-5">
                            <input class="form-check-input" type="checkbox" name="Издательство"
                                   id="exampleRadios1" value="DC">
                            <label class="form-check-label" for="DC">
                                DC
                            </label>
                        </div>
                        <div class="form-check pr-5">
                            <input class="form-check-input" type="checkbox" name="Издательство"
                                   id="exampleRadios1" value="Bubble">
                            <label class="form-check-label" for="Bubble">
                                Bubble
                            </label>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-warning" type="submit">Добавить скидку на товары</button>
            </form>
        </div>
    </main>
</@c.page>
